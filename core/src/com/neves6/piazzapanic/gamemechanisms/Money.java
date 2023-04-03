package com.neves6.piazzapanic.gamemechanisms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Class to handle money which can be used to unlocked machines. */
public class Money {
  private Float balance;
  private Map<String, ArrayList<Float>> unlockDetails;

  /** Constructor where you always start with 0 balance. */
  public Money() {
    this.balance = (float) 0;
    this.unlockDetails = new HashMap();
    // Anything that doesn't need unlocking.
    ArrayList<Float> tempNull = new ArrayList<>();
    // First index represents the price and seconds represents a boolean value represented as an
    // int.
    tempNull.add(0f);
    tempNull.add(1f);
    unlockDetails.put("auto", tempNull);
  }

  /**
   * Pretty print method to display the balance.
   *
   * @return A message that states what the balance is.
   */
  public String displayBalance() {
    return "Balance: $" + this.balance;
  }

  /** Increments balance by a fixed amount. */
  public void incrementBalance() {
    this.balance += 100;
  }

  /**
   * Method to add a group of unlockable machines.
   *
   * @param key String given to identify set of unlockable machines.
   * @param unlockFee Integer given to identify how much money is needed to unlock the machine.
   * @return Boolean value to indicate whether the new group has successfully been added.
   */
  public boolean addGroup(String key, Float unlockFee) {
    // If the key doesn't already exist don't add it.
    if (unlockDetails.containsKey(key)) {
      return false;
    } else {
      ArrayList<Float> tempList = new ArrayList<>();
      tempList.add(unlockFee);
      tempList.add(0f);
      unlockDetails.put(key, tempList);
      return true;
    }
  }

  /**
   * Tells you whether a group has already been unlocked.
   *
   * @param unlockID A string representing the group of machines.
   * @return Boolean value indicating whether a group has already been unlocked or not.
   */
  public boolean isUnlocked(String unlockID) {
    ArrayList<Float> i = unlockDetails.get(unlockID);
    return i.get(1) == 1f;
  }

  /**
   * Method to unlock a machine.
   *
   * @param unlockID A string representing the group of machines.
   * @return Boolean value to represent if the machine has successfully been unlocked or not.
   */
  public boolean unlockMachine(String unlockID) {
    ArrayList<Float> i = unlockDetails.get(unlockID);
    // Must not already be unlocked and have enough money
    // to unlock it.
    if (this.balance >= i.get(0) && i.get(1) == 0) {
      this.balance -= i.get(0);
      i.set(1, 1f);
      return true;
    } else {
      return false;
    }
  }

  public Map<String, ArrayList<Float>> getUnlockDetails() {
    return unlockDetails;
  }
}
