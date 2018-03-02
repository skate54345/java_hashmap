import org.junit.*;
import java.util.List;
import java.util.ArrayList;

public class HashSetTest
{
  private ISet<String> set;

  private <E> void assertArrayEquals(E[] control, E[] target)
  {
    String assertError = "Expected " + stringifyArray(control) +
        " but found " + stringifyArray(target) +".";
    Assert.assertEquals(assertError, control.length, target.length);
    for(int i = 0; i < control.length; i++)
    {
      Assert.assertEquals(assertError, control[i], target[i]);
    }
  }

  private <E> String stringifyArray(E[] array)
  {
    if(array == null) return "null";
    String result = "[";
    for(int i = 0; i < array.length; i++)
    {
      if(i != 0) result += ", ";
      if(array[i] == null) result += "null";
      else result += array[i].toString();
    }
    result += "]";
    return result;
  }

  @Before
  public void setUp()
  {
    set = new HashSet<String>(10);
  }

  @Test
  public void testInitialization()
  {
    /* testing setUp */
  }

  @Test
  public void testSize0()
  {
    Assert.assertEquals(0,set.size());
  }

  @Test
  public void testInternalArraySize()
  {
    Assert.assertEquals(10, set.getInternalArray().length);
  }

  @Test
  public void testHasNot()
  {
    Assert.assertFalse(set.has("Absent"));
  }

  @Test
  public void testHasInternal()
  {
    String element = "Alpha";
    set.add(element);
    Object[] array = set.getInternalArray();
    Object[] control = {null,null,null,null,null,null,element,null,null,null};
    assertArrayEquals(control, array);
  }

  @Test
  public void testSize1()
  {
    String element = "Alpha";
    set.add(element);
    Assert.assertEquals(1,set.size());
  }

  @Test
  public void testAddHas()
  {
    String element = "Alpha";
    set.add(element);
    Assert.assertTrue(set.has(element));
  }

  // this test will fail if you are checking for exact equality rather than
  // utilizing the .equals method.
  @Test
  public void testAddHasEqualityCheck()
  {
    String element = "alpha";
    set.add(element);
    Assert.assertTrue(set.has("Alpha".toLowerCase()));
  }

  @Test
  public void testAddHasNot2()
  {
    String element = "Alpha";
    set.add(element);
    Assert.assertFalse(set.has("Beta"));
  }

  @Test
  public void testAddHasNotCollision()
  {
    String element = "Alpha";
    set.add(element);
    Assert.assertFalse(set.has("Alpha2"));
  }

  // This tests that two items that should hash to the same location workt
  // through double hashing.
  @Test
  public void testHasInternalDoubleHashing()
  {
    String element = "Alpha";
    String element2 = "Alpha2"; // chosen to collide with "Alpha"
    set.add(element);
    set.add(element2);
    Object[] array = set.getInternalArray();
    Object[] control = {null,null,element2,null,null,null,element,null,null,null};
    assertArrayEquals(control, array);
  }

  @Test
  public void testAddHasDoubleHashing()
  {
    String element = "Alpha";
    String element2 = "Alpha2"; // chosen to collide with "Alpha"
    set.add(element);
    set.add(element2);
    Assert.assertTrue(set.has(element));
    Assert.assertTrue(set.has(element2));
  }

  @Test
  public void testSize2()
  {
    String element = "Alpha";
    String element2 = "Beta";
    set.add(element);
    set.add(element2);
    Assert.assertEquals(2,set.size());
  }

  @Test
  public void testToString()
  {
    String element = "Alpha";
    String element2 = "Beta";
    set.add(element);
    set.add(element2);
    Assert.assertEquals("[Beta, Alpha]",set.toString());
  }

  @Test
  public void testLargeSet()
  {
    ISet<String> largeSet = new HashSet<String>(200);
    List<String> pool = new ArrayList<String>();
    for(int i = 0; i < 100; i++)
    {
      pool.add(""+i);
      if(i % 2 == 0) largeSet.add(pool.get(i));
    }
    for(int i = 0; i < 100; i++)
    {
      if(i % 2 == 0) Assert.assertTrue("Should have found " + pool.get(i) + " but did not.", largeSet.has(pool.get(i)));
      else Assert.assertFalse("Should have not found " + pool.get(i) + " but was found.", largeSet.has(pool.get(i)));
    }
  }
}
