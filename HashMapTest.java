import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashMapTest
{
  private IMap<String,Integer> map;

  private String[] pairArrayToStringArray(Object[] pairs)
  {
    String[] strings = new String[pairs.length];
    for(int i = 0; i < pairs.length; i++)
    {
      if(pairs[i] == null)
      {
        strings[i] = null;
        continue;
      }
      String s = "";
      IMap.IMapPair<Object,Object> pair = (IMap.IMapPair<Object,Object>) pairs[i];
      while(pair != null)
      {
        if(s.length() != 0) s += ", ";
        Assert.assertNotNull(pair.getKey());
        Assert.assertNotNull(pair.getElement());
        Assert.assertNotNull(pair.toString());
        s += pair.toString();

        pair = pair.next();
      }
      strings[i] = "["+s+"]";
    }

    return strings;
  }

  private <E> void assertArrayEquals(
      E[] control,
      E[] target)
  {
    String assertError = "Expected " + stringifyArray(control) +
        " but found " + stringifyArray(target) +".";
    Assert.assertNotNull(assertError, target);
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
    map = new HashMap<String,Integer>(10);
  }

  @Test
  public void testInitialization()
  {
    /* testing setUp */
  }

  @Test
  public void testSize0()
  {
    Assert.assertEquals(0, map.size());
  }

  @Test
  public void testInternalArrayEmpty()
  {
    Object[] control = {
      null,null,null,null,null,
      null,null,null,null,null };
    assertArrayEquals(control, map.getInternalArray());
  }

  @Test(expected=NoSuchElementException.class)
  public void testGetEmpty()
  {
    map.get("Absent");
  }

  @Test
  public void testStringEmpty()
  {
    Assert.assertEquals("[]",map.toString());
  }


  @Test
  public void testSize1()
  {
    String key = "Alpha";
    Integer element = 1;
    map.put(key, element);
    Assert.assertEquals(1, map.size());
  }

  @Test(expected=NoSuchElementException.class)
  public void testGetAbsent()
  {
    String key = "Alpha";
    Integer element = 1;
    map.put(key, element);
    map.get("Absent");
  }

  @Test
  public void testGetPresent()
  {
    String key = "Alpha";
    Integer element = 1;
    map.put(key, element);
    Assert.assertEquals(element, map.get("Alpha"));
  }

  // this test will fail if you are checking for exact equality rather than
  // utilizing the .equals method.
  @Test
  public void testGetPresentEqualityCheck()
  {
    String key = "alpha";
    Integer element = 1;
    map.put(key,element);
    Assert.assertEquals(element, map.get("Alpha".toLowerCase()));
  }

  @Test
  public void testString1()
  {
    String key = "Alpha";
    Integer element = 1;
    map.put(key, element);
    Assert.assertEquals("[Alpha: 1]",map.toString());
  }

  @Test
  public void testInternalAfterPut()
  {
    String key = "Alpha";
    Integer element = 1;
    map.put(key, element);

    String[] control = {
       null,null,null,null,null,
       null,"[Alpha: 1]",null,null,null };
    Object[] internal = map.getInternalArray();
    String[] internalStrings = pairArrayToStringArray(internal);

    assertArrayEquals(control, internalStrings);
  }

  @Test
  public void testGetPresentSize2()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    map.put(key, element);
    map.put(key2, element2);
    Assert.assertEquals(element, map.get(key));
    Assert.assertEquals(element2, map.get(key2));
  }

  @Test
  public void testString2()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    map.put(key, element);
    map.put(key2, element2);
    Assert.assertEquals("[Beta: 2, Alpha: 1]",map.toString());
  }

  @Test
  public void testInternalAfterPut2()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    map.put(key, element);
    map.put(key2, element2);

    String[] control = {
       "[Beta: 2]",null,null,null,null,
       null,"[Alpha: 1]",null,null,null };
    Object[] internal = map.getInternalArray();
    String[] internalStrings = pairArrayToStringArray(internal);

    assertArrayEquals(control, internalStrings);
  }

  @Test
  public void testGetPresentWithCollision()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    String key3 = "Delta8"; // chosen to collide with alpha
    Integer element3 = 3;
    map.put(key, element);
    map.put(key2, element2);
    map.put(key3, element3);
    Assert.assertEquals(element, map.get(key));
    Assert.assertEquals(element2, map.get(key2));
    Assert.assertEquals(element3, map.get(key3));
  }

  @Test
  public void testStringWithCollision()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    String key3 = "Delta8"; // chosen to collide with alpha
    Integer element3 = 3;
    map.put(key, element);
    map.put(key2, element2);
    map.put(key3, element3);
    Assert.assertEquals("[Beta: 2, Alpha: 1, Delta8: 3]",map.toString());
  }

  @Test
  public void testInternalWithCollision()
  {
    String key = "Alpha";
    Integer element = 1;
    String key2 = "Beta";
    Integer element2 = 2;
    String key3 = "Delta8"; // chosen to collide with alpha
    Integer element3 = 3;
    map.put(key, element);
    map.put(key2, element2);
    map.put(key3, element3);

    String[] control = {
       "[Beta: 2]",null,null,null,null,
       null,"[Alpha: 1, "+key3+": 3]",null,null,null };
    Object[] internal = map.getInternalArray();
    String[] internalStrings = pairArrayToStringArray(internal);

    assertArrayEquals(control, internalStrings);
  }

  @Test
  public void testLargeMap()
  {
    IMap<String,Integer> largeMap = new HashMap<String,Integer>(200);
    List<String> keypool = new ArrayList<String>();
    List<Integer> elementpool = new ArrayList<Integer>();
    for(int i = 0; i < 100; i++)
    {
      keypool.add(""+i);
      elementpool.add((i+20) - (i/4));
      if(i % 2 == 0) largeMap.put(keypool.get(i),elementpool.get(i));
    }
    for(int i = 0; i < 100; i++)
    {
      if(i % 2 == 0) Assert.assertEquals("Should have found " + elementpool.get(i) + " for key "+elementpool.get(i)+" but did not.", elementpool.get(i), largeMap.get(keypool.get(i)));
      else
      {
        try
        {
          Integer element = largeMap.get(keypool.get(i));
          // shouldnt be in the set, so fail if we there was no excpetion
          Assert.fail("Should have not found entry for " + keypool.get(i) + " but "+element+" was found.");
        }
        catch(NoSuchElementException e)
        { /* great */ }
      }
    }
  }

}
