package com.example.increaseyouriq;

import android.text.format.Time;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

class Robot {
    Alarm alarm;
    Coffee coffee;
    Water water;
    Bag bag;
    BreakFast breakFast;
    Lunch lunch;
    Calendar calendar;
    int day;

    public Robot(Alarm alarm, Coffee coffee, Water water, Bag bag, BreakFast breakFast, Lunch lunch) {
        this.alarm = alarm;
        this.coffee = coffee;
        this.water = water;
        this.bag = bag;
        this.breakFast = breakFast;
        this.lunch = lunch;

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void ringAlarm(){
        Time currTime=new Time(String.valueOf(LocalDateTime.now()));
        if(alarm.getRepeatDayOfWeek()[day-1]==1 && currTime == alarm.getTime()){
            System.out.println("Ringing Alarm\n"+alarm.toString());
        }


    }
    public void makeCoffee(){

        switch (day) {
            case Calendar.SUNDAY:
                coffee.setMilk(0);
                break;
            case Calendar.MONDAY:
                coffee.setTopping("Chocolate");
                break;
            case Calendar.TUESDAY:
                coffee.setCocoaPowder(60);
                coffee.setMilk(20);
                break;
        }

        System.out.println("Making Coffe \n"+coffee.toString());
    }
    public void heatWater(){
        if (water.getDayOfWeek()[day-1]==1){
            System.out.println("Heating water\n"+water.toString());
        }
    }
    public void packBag(){}
    public void cookLunch(){
        System.out.println("Cooking Food");
        System.out.println("Breakfast = "+breakFast.getMenu());
        System.out.println("Lunch = "+lunch.getMenu());
    }
    public void ironClothes(){
        System.out.println("Ironing Clothes");
    }
}

class Alarm{
    Time time;
    Date date;
    int snooze;
    int[] repeatDayOfWeek;

    public Alarm(Time time, Date date, int snooze, int[] repeatDayOfWeek) {
        this.time = time;
        this.date = date;
        this.snooze = snooze;
        this.repeatDayOfWeek = repeatDayOfWeek;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSnooze() {
        return snooze;
    }

    public void setSnooze(int snooze) {
        this.snooze = snooze;
    }

    public int[] getRepeatDayOfWeek() {
        return repeatDayOfWeek;
    }

    public void setRepeatDayOfWeek(int[] repeatDayOfWeek) {
        this.repeatDayOfWeek = repeatDayOfWeek;
    }
}

class Coffee{
    String name;
    int sugar;
    int cocoaPowder;
    int milk;
    String topping;

    public Coffee(String name,int sugar, int cocoaPowder, int milk, String topping) {
        this.name=name;
        this.sugar = sugar;
        this.cocoaPowder = cocoaPowder;
        this.milk = milk;
        this.topping = topping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getCocoaPowder() {
        return cocoaPowder;
    }

    public void setCocoaPowder(int cocoaPowder) {
        this.cocoaPowder = cocoaPowder;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", sugar=" + sugar +
                ", cocoaPowder=" + cocoaPowder +
                ", milk=" + milk +
                ", topping='" + topping + '\'' +
                '}';
    }
}
class Water{
    int temprature;
    int[] dayOfWeek;

    public Water(int temprature, int[] dayOfWeek) {
        this.temprature = temprature;
        this.dayOfWeek = dayOfWeek;
    }

    public Water(int temprature) {
        this.temprature = temprature;
    }

    public int getTemprature() {
        return temprature;
    }

    public void setTemprature(int temprature) {
        this.temprature = temprature;
    }

    public int[] getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int[] dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
class Bag{
    ArrayList<Book> books=new ArrayList<>();
}
class Book{
    String name;
    String author;
}
class BreakFast{
    ArrayList<String> menu;

    public BreakFast(ArrayList<String> menu) {
        ArrayList<String> availableMenu=new ArrayList<>();
        availableMenu.add("Salad");
        availableMenu.add("Cornflakes");
        availableMenu.add("Bread");
        availableMenu.add("Omelete");
        //Add more menus
        this.menu = availableMenu;
    }
    public String  getMenu() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(menu.size());
        return menu.get(index);
    }
}
class Lunch{
    ArrayList<String> menu;

    public Lunch() {
        ArrayList<String> availableMenu=new ArrayList<>();
        availableMenu.add("Steak");
        availableMenu.add("Rice");
        availableMenu.add("Beef");
        availableMenu.add("Pasta");
        //Add more menus
        this.menu = availableMenu;
    }
    public String  getMenu() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(menu.size());
        return menu.get(index);
    }
}