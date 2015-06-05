package raven.sqf.tests

import org.junit.Test
import javax.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import raven.sQF.Model
import org.junit.Assert
import raven.validation.SQFValidator
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import raven.SQFInjectorProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(SQFInjectorProvider))

class SQF_ValidatorFunctionsTest {
	@Inject extension ParseHelper<Model>
	
	@Test
	def void getTypeTest() {
		val model = '''
						test1 = 3;
						test2 = "Mama";
						test3 = [3];
						test4 = ("test" + "test");
						test5 = -(3 + 4 - test1)
						test6 = ([3] + [2,5]);
						
						refTest1 = test1;
						refTest2 = test2;
						refTest3 = test3;
						refTest4 = test4;
						refTest5 = test5;
						refTest6 = test6;
						
						test7 = true;
						test8 = false;
						test9 = (3 < 4);
						test10 = "Mama" == "Papa";
						
						refTest7 = test7;
						refTest8 = test8;
						reftest9 = test9;
						refTest10 = test10;
						reftest11 = test1 isEqualTo test2;
					'''.parse
					
		val vCon1 = model.elements.get(0).dec
		Assert.assertEquals("number", SQFValidator.getType(vCon1))
		
		val vCon2 = model.elements.get(1).dec
		Assert.assertEquals("string", SQFValidator.getType(vCon2))
		
		val vCon3 = model.elements.get(2).dec
		Assert.assertEquals("array", SQFValidator.getType(vCon3))
		
		val vCon4 = model.elements.get(3).dec
		Assert.assertEquals("string", SQFValidator.getType(vCon4))
		
		val vCon5 = model.elements.get(4).dec
		Assert.assertEquals("number", SQFValidator.getType(vCon5))
		
		val vCon6 = model.elements.get(5).dec
		Assert.assertEquals("array", SQFValidator.getType(vCon6))
		
		val vCon7 = model.elements.get(6).dec
		Assert.assertEquals("number", SQFValidator.getType(vCon7))
		
		val vCon8 = model.elements.get(7).dec
		Assert.assertEquals("string", SQFValidator.getType(vCon8))
		
		val vCon9 = model.elements.get(8).dec
		Assert.assertEquals("array", SQFValidator.getType(vCon9))
		
		val vCon10 = model.elements.get(9).dec
		Assert.assertEquals("string", SQFValidator.getType(vCon10))
		
		val vCon11 = model.elements.get(10).dec
		Assert.assertEquals("number", SQFValidator.getType(vCon11))
		
		val vCon12 = model.elements.get(11).dec
		Assert.assertEquals("array", SQFValidator.getType(vCon12))
		
		val vCon13 = model.elements.get(12).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon13))
		
		val vCon14 = model.elements.get(13).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon14))
		
		val vCon15 = model.elements.get(14).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon15))
		
		val vCon16 = model.elements.get(15).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon16))
		
		val vCon17 = model.elements.get(16).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon17))
		
		val vCon18 = model.elements.get(17).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon18))
		
		val vCon19 = model.elements.get(18).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon19))
		
		val vCon20 = model.elements.get(19).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon20))
		
		val vCon21 = model.elements.get(20).dec
		Assert.assertEquals("boolean", SQFValidator.getType(vCon21))
		
		
		Assert.assertEquals("number", SQFValidator.getType(vCon15.brCon.decCon.singleContent))
		
		
		val model2 = '''
					 	test1 = "Mama";
					 	test2 = "test" + test1;
					 	test3 = 3;
					 	test4 = test3 * 4 + test3;
					 	test5 = test1 isEqualTo test3;
					 	
					 	if(test1 == test2) then {
					 		
					 	};
					 '''.parse
					 
		val input1 = model2.elements.get(0).dec.brCon.decCon.singleContent
		Assert.assertEquals("string", SQFValidator.getType(input1))
		
		val input2 = model2.elements.get(1).dec.brCon.decCon.nextCon.get(0)
		Assert.assertEquals("string", SQFValidator.getType(input2))
		
		val input3 = model2.elements.get(2).dec.brCon.decCon.singleContent
		Assert.assertEquals("number", SQFValidator.getType(input3))
		
		val input4 = model2.elements.get(3).dec.brCon.decCon.nextCon.get(1)
		Assert.assertEquals("number", SQFValidator.getType(input4))
		
		val input5 = model2.elements.get(4).dec.brCon.decCon.singleContent
		Assert.assertEquals("string", SQFValidator.getType(input5))
		
		val input6 = model2.elements.get(4).dec.brCon.content.get(0).singleContent
		Assert.assertEquals("number", SQFValidator.getType(input6))
		
		val input7 = model2.elements.get(5).control.ifStat.condition.boolCon.decCon.singleContent
		Assert.assertEquals("string", SQFValidator.getType(input7))
		
		val input8 = model2.elements.get(5).control.ifStat.condition.boolCon.content.get(0).singleContent
		Assert.assertEquals("string", SQFValidator.getType(input8))
	}
	
	@Test
	def void getThatReferenceTest() {
		val model = '''
						test1 = 3;
						test2 = test1;
						test3 = test2;
					'''.parse
					
		val result = model.elements.get(0).dec.brCon.decCon
		
		val input1 = model.elements.get(1).dec.brCon.decCon.singleContent
		Assert.assertEquals(result, SQFValidator.getThatReference(input1))
		
		val input2 = model.elements.get(2).dec.brCon.decCon.singleContent
		Assert.assertEquals(result, SQFValidator.getThatReference(input2))
	}
	
	@Test
	def void getBracketContentTest() {
		val model = '''
						test1 = (3+4);
						test2 = ("mama"+"papa");
						test3 = (test1)
						test4 = 4;
					'''.parse
					
		val result1 = model.elements.get(0).dec.brCon.decCon.singleContent.embrCon
		val input1 = model.elements.get(0).dec.brCon.decCon.singleContent
		Assert.assertEquals(result1, SQFValidator.getBracketContent(input1))
		
		val result2 = model.elements.get(1).dec.brCon.decCon.singleContent.embrCon
		val input2 = model.elements.get(1).dec.brCon.decCon.singleContent
		Assert.assertEquals(result2, SQFValidator.getBracketContent(input2))
		
		val result3 = model.elements.get(2).dec.brCon.decCon.singleContent.embrCon
		val input3 = model.elements.get(2).dec.brCon.decCon.singleContent
		Assert.assertEquals(result3, SQFValidator.getBracketContent(input3))
		
		val result4 = model.elements.get(3).dec.brCon
		val input4 = model.elements.get(3).dec.brCon.decCon.singleContent
		Assert.assertEquals(result4, SQFValidator.getBracketContent(input4))
	}
	
	@Test
	def void getParentNameTest() {
		val model = '''
						test1 = "Miau"
						test2 = test1;
						test3 = 3;
						test4 = (test1 + "Test");
					'''.parse
					
		val input1 = model.elements.get(0).dec.brCon.decCon.singleContent
		Assert.assertEquals("test1", SQFValidator.getParentName(input1))
		
		val input2 = model.elements.get(1).dec.brCon.decCon.singleContent
		Assert.assertEquals("test2", SQFValidator.getParentName(input2))
		
		val input3 = model.elements.get(2).dec.brCon.decCon.singleContent
		Assert.assertEquals("test3", SQFValidator.getParentName(input3))
		
		val input4 = model.elements.get(3).dec.brCon.decCon.singleContent
		Assert.assertEquals("test4", SQFValidator.getParentName(input4))
	}
	
	@Test
	def void getParentTest() {
		val model = '''
						test1 = 4;
						test2 = "Test";
						test3 = test1;
						test4 = ((3+4)*(-18));
					'''.parse
					
		val result1 = model.elements.get(0).dec
		val input1 = result1.brCon.decCon.singleContent
		Assert.assertEquals(result1, SQFValidator.getParent(input1))
		
		val result2 = model.elements.get(1).dec
		val input2 = result2.brCon.decCon.singleContent
		Assert.assertEquals(result2, SQFValidator.getParent(input2))
		
		val result3 = model.elements.get(2).dec
		val input3 = result3.brCon.decCon.singleContent
		Assert.assertEquals(result3, SQFValidator.getParent(input3))
		
		val result4 = model.elements.get(3).dec
		val input4 = result4.brCon.decCon.singleContent
		Assert.assertEquals(result4, SQFValidator.getParent(input4))
	}
	
	@Test
	def void getReferenceNameTest() {
		val model = '''
						test1 = "Mama";
						test2 = test1;
						test3 = test2 + test1;
					'''.parse
					
		val input1 = model.elements.get(1).dec.brCon.decCon.singleContent
		Assert.assertEquals("test1", SQFValidator.getReferenceName(input1))
		
		val input2 = model.elements.get(2).dec.brCon.decCon.singleContent
		Assert.assertEquals("test2", SQFValidator.getReferenceName(input2))
		
		val input3 = model.elements.get(2).dec.brCon.decCon.nextCon.get(0)
		Assert.assertEquals("test1", SQFValidator.getReferenceName(input3))
	}
}