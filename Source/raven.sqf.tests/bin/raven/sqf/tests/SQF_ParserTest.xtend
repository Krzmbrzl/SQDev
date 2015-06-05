package raven.sqf.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import raven.SQFInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.junit.Test
import javax.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import raven.sQF.Model
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Assert

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(SQFInjectorProvider))

class SQF_ParserTest {
	@Inject extension ParseHelper<Model>
	@Inject extension ValidationTestHelper
	
	@Test
	def void TestDeclarations() {
		'''
			name = "Miau";
			_andererName = 34;
			undNochEiner = name;
			test1 = -1 * -(4+-18)^2;
			
			array = [4,"Test",name];
			array2 = array;
			array3 = +array2;
			
			if(name == ("Miau" + "Mama")) then {
				if(true) then {
					if(!(3<4)) then [{
						test = 4;
					},{
						test = 5;
					}];
				};
			}else {
				
			};
			
			if(true) exitWith {test = 5;};
			
			while{name isEqualTo array} do {test1 = 3; test2 = 4; test3 = test1;};
			
			for "i" from 2 to 8 do {test1 = 4; test2 = 8; test3 = test1; test=i;};
			
			for "i" from 2 to 8 step 4 do {};
			
			for [{i=0}, {i<4}, {i=i+1}] do {test = i; test2 = "Mama";};
			
			{
				test1 = "Mama";
				test2 = 3;
				test3 = test1;
				test4 = _x;
			} forEach [3,4,6];
			
			switch(name) do {
				case "Mama": {
					test1 = 3;
					test2 = 4;
					test3 = test1;
				};
				
				case "Trottel": {
					
				};
				
				default {
					test=5;
				};
			};
			
			[] spawn test1;
			miau = [] call test1;
		'''.parse.assertNoErrors //TODO
		
		
		val model = '''
			name = "Miau";
			_andererName = 34;
			undNochEiner = name;
			test1 = -1 * -(4+-18)^2;
			
			array = [4,"Test",name];
			array2 = array;
			array3 = +array2;
		'''.parse
		
		val dec1 = model.elements.get(0) //name = "Miau";
		Assert.assertEquals("name", dec1.dec.name)
		Assert.assertEquals('Miau', dec1.dec.brCon.decCon.singleContent.string)
		
		val dec2 = model.elements.get(1) //name = "_andererName";
		Assert.assertEquals("_andererName", dec2.dec.name)
		Assert.assertEquals("34", dec2.dec.brCon.decCon.singleContent.num)
		
		val dec3 = model.elements.get(2) //name = "undNochEiner";
		Assert.assertEquals("undNochEiner", dec3.dec.name)
		Assert.assertEquals("name", dec3.dec.brCon.decCon.singleContent.reference.name)
		
		val dec4 = model.elements.get(3) //name = "test1";
		Assert.assertEquals("test1", dec4.dec.name)
		Assert.assertEquals("-", dec4.dec.brCon.decCon.singleContent.unOP)
		Assert.assertEquals("1", dec4.dec.brCon.decCon.singleContent.num)
		Assert.assertEquals("-", dec4.dec.brCon.decCon.nextCon.get(0).unOP)
	}
}