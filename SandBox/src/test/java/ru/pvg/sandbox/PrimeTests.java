package ru.pvg.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
   Created Владимир  at 16:23  12.05.2019
*/
public class PrimeTests {

  @Test (enabled = false)
  public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }


  @Test(enabled = false)
  public void testPrimeLong() {
    long n =  Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrimeLong(n));
  }

  @Test (enabled = false)
  public void testNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }



}
