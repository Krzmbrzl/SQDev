package raven.sqdev.fileSystemListener;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.interfaces.IFileSystemChangeListener;

/**
 * A class used to manage <code>FileSystemListeners</code>
 * 
 * @author Raven
 *
 */
public class FileSystemWatcher {
	
	/**
	 * The default instance of this class
	 */
	private static FileSystemWatcher defaultInstance;
	
	
	/**
	 * The watchService used by this instance
	 */
	private WatchService watchService;
	/**
	 * Indicating whether the loop for checking the watcher is currently running
	 */
	private AtomicBoolean isWatching;
	/**
	 * The flag indicating whether the loop checking for changes should continue
	 */
	private AtomicBoolean continueChecking;
	/**
	 * The list of registered listeners
	 */
	private List<IFileSystemChangeListener> listenerList;
	/**
	 * A map containing the watched paths with their associated WatchKeys
	 */
	private Map<Path, WatchKey> watchKeys;
	/**
	 * The queue of listeners that want to be registered
	 */
	private List<IFileSystemChangeListener> listenerAddQueue;
	/**
	 * The queue of listeners that want to be unregistered
	 */
	private List<IFileSystemChangeListener> listenerRemoveQueue;
	/**
	 * The <code>Lock</code> for the {@link #listenerAddQueue}
	 */
	private Lock addQueueLock;
	/**
	 * The <code>Lock</code> for the {@link #listenerRemoveQueue}
	 */
	private Lock removeQueueLock;
	/**
	 * The <code>Lock</code> for the {@link #listenerList}
	 */
	private Lock listenerLock;
	
	
	/**
	 * This constructor should only be used if you want to create a new instance
	 * of a <code>WatchService</code>.<br>
	 * Otherwise you want to access an instance of this class via
	 * {@link #getDefault()}
	 */
	public FileSystemWatcher() {
		isWatching = new AtomicBoolean(false);
		continueChecking = new AtomicBoolean(true);
		
		listenerList = new ArrayList<IFileSystemChangeListener>();
		listenerAddQueue = new ArrayList<IFileSystemChangeListener>();
		listenerRemoveQueue = new ArrayList<IFileSystemChangeListener>();
		
		watchKeys = Collections.synchronizedMap(new HashMap<Path, WatchKey>());
		
		addQueueLock = new ReentrantLock();
		removeQueueLock = new ReentrantLock();
		listenerLock = new ReentrantLock();
	}
	
	/**
	 * Gets the default instance of this class
	 */
	public static FileSystemWatcher getDefault() {
		if (defaultInstance == null) {
			defaultInstance = new FileSystemWatcher();
		}
		
		return defaultInstance;
	}
	
	/**
	 * Starts watching the given path
	 * 
	 * @param path
	 *            The path to watch
	 * @return The respective <code>WatchKey</code> associated with the added
	 *         path
	 */
	protected WatchKey watchPath(Path path) {
		try {
			// register path to register
			WatchKey key = path.register(getWatchService(),
					new WatchEvent.Kind<?>[] {
							StandardWatchEventKinds.ENTRY_CREATE,
							StandardWatchEventKinds.ENTRY_DELETE,
							StandardWatchEventKinds.ENTRY_MODIFY });
			
			// start watching if not already running
			startWatching();
			
			return key;
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Starts the loop continuously watching for changes
	 */
	protected void startWatching() {
		if (isWatching.get()) {
			return;
		}
		
		isWatching.set(true);
		continueChecking.set(true);
		
		Thread checkThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int interruptions = 0;
				
				try {
					while (continueChecking.get()) {
						try {
							if (interruptions > 3) {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
									
									throw new SQDevCoreException(
											"Interrupted FileSystemWatcher!",
											e);
								}
							}
							
							WatchKey key = watchService.poll(1,
									TimeUnit.SECONDS);
							
							interruptions = 0;
							
							if (key != null) {
								// Dequeueing events
								for (WatchEvent<?> watchEvent : key
										.pollEvents()) {
									Kind<?> eventKind = watchEvent.kind();
									
									if (eventKind == StandardWatchEventKinds.OVERFLOW) {
										continue; // do nothing
									}
									
									EFileChangeType type;
									
									if (eventKind == StandardWatchEventKinds.ENTRY_CREATE) {
										type = EFileChangeType.CREATED;
									} else {
										if (eventKind == StandardWatchEventKinds.ENTRY_DELETE) {
											type = EFileChangeType.DELETED;
										} else {
											type = EFileChangeType.CHANGED;
										}
									}
									
									Path root = (Path) key.watchable();
									Path target = (Path) watchEvent.context();
									
									watchedPathChanged(root, target, type);
								}
								
								// reset key
								key.reset();
							}
							
							if (watchKeys.size() == 0) {
								// There are no watched paths anymore -> exit
								// loop
								continueChecking.set(false);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
							
							interruptions++;
						}
					}
				} finally {
					isWatching.set(false);
				}
			}
		});
		
		checkThread.start();
	}
	
	/**
	 * Gets the watch service used by this instance
	 * 
	 * @throws IOException
	 */
	protected WatchService getWatchService() throws IOException {
		if (watchService == null) {
			watchService = FileSystems.getDefault().newWatchService();
		}
		
		return watchService;
	}
	
	/**
	 * Gets called every time a
	 * 
	 * @param root
	 *            The root path (The one that has been registered for watching)
	 * @param target
	 *            The actula modified file within the root
	 * @param type
	 *            The type of the change
	 */
	protected void watchedPathChanged(Path root, Path target,
			EFileChangeType type) {
		for (IFileSystemChangeListener currentListener : getRegisteredListeners()) {
			if (root.equals(currentListener.getConfiguredPath())) {
				// Notify listener about change
				currentListener
						.changed(new FileSystemChangeEvent(type, root, target));
			}
		}
	}
	
	/**
	 * Gets the list of currently registered listeners
	 */
	protected List<IFileSystemChangeListener> getRegisteredListeners() {
		// prevent concurrent access
		listenerLock.lock();
		addQueueLock.lock();
		
		// Add Queue to the registered list
		listenerList.addAll(listenerAddQueue);
		listenerAddQueue.clear();
		
		addQueueLock.unlock();
		
		removeQueueLock.lock();
		
		// remove Queue from the registered list
		listenerList.removeAll(listenerRemoveQueue);
		listenerRemoveQueue.clear();
		
		removeQueueLock.unlock();
		listenerLock.unlock();
		
		
		return listenerList;
	}
	
	/**
	 * Adds the given <code>IFileSystemChangeListener</code> to this watcher
	 * 
	 * @param listener
	 *            The listener to add
	 */
	public void addFileSystemListener(IFileSystemChangeListener listener) {
		// prevent concurrent access
		addQueueLock.lock();
		
		// add listener
		listenerAddQueue.add(listener);
		
		addQueueLock.unlock();
		
		if (!watchKeys.containsKey(listener.getConfiguredPath())) {
			// only add if there is no key for it already
			WatchKey key = watchPath(listener.getConfiguredPath());
			
			watchKeys.put(listener.getConfiguredPath(), key);
		}
	}
	
	/**
	 * Removes the given <code>IFileSystemChangeListener</code> from this
	 * watcher
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public void removeFileSystemChangeListener(
			IFileSystemChangeListener listener) {
		// prevent concurrent access
		removeQueueLock.lock();
		
		// add listener
		listenerRemoveQueue.add(listener);
		
		removeQueueLock.unlock();
		
		// check if WatchKey has to be removed
		boolean pathStillWatched = false;
		
		listenerLock.lock();
		for (IFileSystemChangeListener currentListener : getRegisteredListeners()) {
			if (currentListener.getConfiguredPath()
					.equals(listener.getConfiguredPath())) {
				pathStillWatched = true;
				break;
			}
		}
		listenerLock.unlock();
		
		if (!pathStillWatched) {
			// cancel and remove WatchKey as it is no longer needed
			watchKeys.get(listener.getConfiguredPath()).cancel();
			watchKeys.remove(listener.getConfiguredPath());
		}
	}
}
