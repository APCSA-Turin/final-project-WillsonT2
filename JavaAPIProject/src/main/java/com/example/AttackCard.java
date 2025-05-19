package com.example;

public class AttackCard extends Card{
     private int dmg;

     public AttackCard(String name, int cost, int dmg){
         super(name, cost, "attack");
         this.dmg = dmg;
     }

     public int getDmg(){
         return dmg;
     }
}
