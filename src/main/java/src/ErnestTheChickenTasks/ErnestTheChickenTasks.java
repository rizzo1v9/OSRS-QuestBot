package src.ErnestTheChickenTasks;

import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.trade.Trade;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.event.impl.GameStateEvent;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.interactive.interact.Interactable;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.methods.map.Tile;

import java.awt.geom.Area;

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walk;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;





public class ErnestTheChickenTasks extends TaskNode {
    //created by JoshTach

    ErnestTheChickenAreas area = new ErnestTheChickenAreas();


    NPC veronica;
    NPC prof;
    GroundItem fishFood;
    GroundItem poison;
    GameObject bookcase;
    GameObject ladder;
    GameObject lever;
    GroundItem oilCan;
    GroundItem spade;
    GameObject compost;
    GameObject fountain;
    GroundItem tube;



    public void startQuest() {
        do {
            //this will walk you to Veronica the quest start
            walkExact(area.Veronica.getRandomTile());
            sleep(1500,2000);
            MethodProvider.log("walking to quest start");
        }
        while (!area.Veronica.contains(getLocalPlayer().getTile()));
        veronica = NPCs.closest("Veronica");
        //scans and talks to veronica to start quest.
        if(veronica != null) {
            if(veronica.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(8000, 10000));
                do {
                    continueDialogue();
                    sleep(2000,3000);
                    MethodProvider.log("gonna see whats good");
                }
                while(Dialogues.canContinue());
                sleep(2000,3000);
                chooseOption(1);

                do {
                    continueDialogue();
                    sleep(2000,3000);
                    MethodProvider.log("gonna see whats good");
                }
                while(Dialogues.canContinue());
            }
        }
    }

    public void talkToProf() {
        //walks us to professor and talks with him to contiune quest.
        do {
            walkExact(area.Professor.getRandomTile());
            sleep(2000,3000);
            MethodProvider.log("walking to the boy");
        }
        while (!area.Professor.contains(getLocalPlayer().getTile()));
        prof = NPCs.closest("Professor Oddenstein");
        if (prof != null) {
            if(prof.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(8000, 10000));
                do {
                    continueDialogue();
                    sleep(2000,3000);
                    MethodProvider.log("wheres ERNEST");
                }
                while(Dialogues.canContinue());
                sleep(2000,3000);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(2000,3000);
                    MethodProvider.log("oh hes the chicken?");
                }
                while(Dialogues.canContinue());
                sleep(2000,3000);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(2000,3000);
                    MethodProvider.log("oh hes the chicken?");
                }
            while (Dialogues.canContinue());
            }
        }
    }

    public void getFishFood(){
        //this will take us to our fish food an dgrab it for us.
        do{
          walkExact(area.FishFood.getRandomTile());
          sleep(2000,3000);
          MethodProvider.log("walking to get food");
        }
        while (!area.FishFood.contains(getLocalPlayer().getTile()));
        fishFood = GroundItems.closest(272);
        //grabs the fish food ID and uses the take interaction
             if (fishFood != null) {
                  if(fishFood.interact("Take")) {
                  sleep(5000,7000);
                  MethodProvider.log("grabbed some mf fish food");

           }
       }
    }

    public void getPoison() {
        //walks us to the poison area
        do{
            walkExact(area.Poison.getRandomTile());
            sleep(2000,3000);
            MethodProvider.log("grabbing that posion");

        }
        while (!area.Poison.contains(getLocalPlayer().getTile()));
        //grabs the poison ID and uses the take interaction
        poison = GroundItems.closest(273);
        if(poison != null) {
            if(poison.interact("Take"));
            sleep(5000,8000);
            MethodProvider.log("got that loud");
        }
    }

    public void underground() {
    //walks us to the underground area and interacts with the bookcase to open the door and climbs us down the ladder
     do {
         walkExact(area.BookCase.getRandomTile());
         sleep(1500,2000);
         MethodProvider.log("walking to bookcase room");
        }
     while(!area.BookCase.contains(getLocalPlayer().getTile()));
         bookcase = GameObjects.closest("Bookcase");
         if(bookcase != null) {
            if(bookcase.interact("Search")){
               sleep(6000,9000);
               MethodProvider.log("opening bookcase");
               ladder = GameObjects.closest("Ladder");
               if(ladder != null){
                   if (ladder.interact("Climb-down")){
                       sleep(4000, 5000);
                       MethodProvider.log("climbing down");

                   }
               }
            }
           }
    }

    public void levers() {
        //this will do the entire basement puzzle and get us the oil can to continue the quest
        do {
         walkExact(area.LeverB);
         sleep(3000,3500);
         MethodProvider.log("cringing to B");
        }
        while (!area.LeverB.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(147);
        if (lever != null){
            if (lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(), Calculations.random(3000,4000));
                MethodProvider.log("pulled B");
            }
        }
        sleep(2000,4000);
        do {
            walkExact(area.LeverA);
            sleep(2500, 3000);
            MethodProvider.log("cringing to A");
        }
        while (!area.LeverA.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(146);
        if (lever != null) {
            if (lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(), Calculations.random(3000,4000));
                MethodProvider.log("pulled A");
           }
        }
        sleep(2000,4000);
        do {
            walkExact(area.LeverD);
            sleep(4000,5000);
            MethodProvider.log("cringing to D");
        }
        while (!area.LeverD.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(149);
        if (lever != null){
            if(lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,4000));
                MethodProvider.log("pulled D");
            }
        }
        sleep(2000,4000);
        do {
            walkExact(area.SouthRoom);
            sleep(4000,5000);
            MethodProvider.log("walking to south room");
        }
        while (!area.SouthRoom.equals(getLocalPlayer().getTile()));
        do {
            walkExact(area.LeverA);
            sleep(4000,5000);
            MethodProvider.log("cringing to A");
        }
        while (!area.LeverA.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(146);
        if (lever != null){
            if (lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(), Calculations.random(3000,4000));
                MethodProvider.log("pulled A");
            }
        }
        sleep(2000,4000);
        do {
            walkExact(area.LeverB);
            sleep(2500, 3000);
            MethodProvider.log("cringing to b");
        }
        while (!area.LeverB.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(147);
        if (lever != null) {
            if (lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(), Calculations.random(3000,4000));
                MethodProvider.log("pulled b");
            }
        }
        sleep(2000,4000);
        do {
            walkExact(area.NWRoom);
            sleep(3000,4000);
            MethodProvider.log("walking to southroom");
        }
        while (!area.NWRoom.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(151);
        if (lever != null){
            if(lever.interact("Pull"));
            sleepUntil(()->getLocalPlayer().isAnimating(), Calculations.random(3000,5000));
            MethodProvider.log("pulled lever F");
        }
        sleep(3000,4000);
        if (lever != null){
            lever = GameObjects.closest(150);
            if (lever.interact("Pull"));
            sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,5000));
            MethodProvider.log("pulled E");
        }
        do {
            walkExact(area.LeverC);
            sleep(3000,4000);
            MethodProvider.log("walking to C");
        }
        while (!area.LeverC.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(148);
        if (lever != null){
            if (lever.interact("Pull"));
            sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,5000));
        }
        sleep(2000,4000);
        do {
            walkExact(area.NWRoom);
            sleep(3000,4000);
            MethodProvider.log("walking to nwroom");
        }
        while (!area.NWRoom.equals(getLocalPlayer().getTile()));
        lever = GameObjects.closest(150);
        if (lever != null){
            if (lever.interact("Pull"));
            sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,5000));
            MethodProvider.log("pulled E");
        }
        sleep(2000,4000);
        do {
            walkExact(area.OilRoom);
            sleep(3000,4000);
            MethodProvider.log("walking to oil room");
        }
        while (!area.OilRoom.equals(getLocalPlayer().getTile()));
        oilCan = GroundItems.closest(277);
        if (oilCan != null){
            if (oilCan.interact("Take")) {
                MethodProvider.log("took oilcan");
                sleep(2000,5000);

            }
        }
    }

    public void poisonFood(){
        //creates the poisoned fish food by using both items on each other
        if (Inventory.contains("Fish food","Poison")){
            if (Inventory.get("Fish food").useOn("Poison")) {
                MethodProvider.log("made poisoned fish food");
                sleep(2000, 4000);
            }
        }
    }

    public void spade(){
        //this will bring us out of basement and grab our spade for us
        do {
            walkExact(area.LeverB);
            sleep(3000,4000);
            MethodProvider.log("walking to ladder");
        }
        while (!area.LeverB.equals(getLocalPlayer().getTile()));
        ladder = GameObjects.closest("Ladder");
        if (ladder != null){
            if (ladder.interact("Climb-up"))
                sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,4000));
            }
        sleep(3000,4000);
        lever = GameObjects.closest("Lever");
        if(lever != null){
            if (lever.interact("Pull")){
                sleepUntil(()->getLocalPlayer().isAnimating(),Calculations.random(3000,4000));
            }
        do {
            walkExact(area.SpadeRoom.getRandomTile());
            sleep(3000,4000);
            MethodProvider.log("walking to spade room");
        }
        while (!area.SpadeRoom.contains(getLocalPlayer().getTile()));
        sleep(1500,2500);
        spade = GroundItems.closest("Spade");
        if (spade != null){
            if(spade.interact("Take"));
            sleep(3000,4000);
        }
      }
    }
    public void key(){
        //this will go ahead and get us to compost heap to dig up our key
        do {
            walkExact(area.Compost);
            sleep(2000,3000);
            MethodProvider.log("walking to compost");
        }
        while (!area.Compost.equals(getLocalPlayer().getTile()));
        sleep(2000,3000);
        compost = GameObjects.closest("Compost heap");
        if (compost != null){
            if (Inventory.get("Spade").useOn(compost)) {
                MethodProvider.log("grabbing key");
                sleepUntil(() -> getLocalPlayer().isAnimating(), Calculations.random(3000, 4000));
            }
        }
    }
    public void gauge(){
        //this will take us to the fountain, kill the fish, continue the dialog, and grab our pressure gage
        do {
            walkExact(area.Fountain);
            sleep(2000,3000);
            MethodProvider.log("walking to fountain");
            }
            while (!area.Fountain.equals(getLocalPlayer().getTile()));
                //scans for fountain and searches
                fountain = GameObjects.closest("Fountain");
                if (fountain != null){
                    if (Inventory.get("Poisoned fish food").useOn(fountain)){
                        sleep(7000,8000);
                        MethodProvider.log("waiting for fish to die");
                    }
                    fountain = GameObjects.closest("Fountain");
                   if (fountain.interact("Search")){
                       //this will sleep until the dialog box pops up, finishes dialog and grabs the pressure guage
                      sleepUntil(()->Dialogues.canContinue(),Calculations.random(3000,4000));
                        do {
                            Dialogues.continueDialogue();
                            sleep(2000,3000);
                            MethodProvider.log("talk");
                        }
                        while(Dialogues.canContinue() && !Inventory.contains("Pressure gauge"));
                        MethodProvider.log("Got pressure gauge");
                    }
               }
           }

    public void tube() {
        //this will walk us to the rubber tube area, grab it and run out (hopefully in time before skeleton kills you)
        do {
            walkExact(area.TubeRoom.getRandomTile());
            sleep(2000,3000);
            MethodProvider.log("walking to grab tube");
        }
        while (!area.TubeRoom.contains(getLocalPlayer().getTile()));
        tube = GroundItems.closest("Rubber tube");
        if (tube != null){
            if(tube.interact("Take")){
                sleepUntil(()->Inventory.contains("Rubber tube"),Calculations.random(2000,3000));
                MethodProvider.log("got tube");
            }
        }
    }
    public void finishQuest() {
        //walks us to professor, talks to him and finishes the quest.
        do {
            walkExact(area.Professor.getRandomTile());
            sleep(1500,2500);
            MethodProvider.log("walking to professor");
        }
        while (!area.Professor.contains(getLocalPlayer().getTile()));
        prof = NPCs.closest("Professor Oddenstein");
        MethodProvider.log(prof);
        if (prof != null){
            if(prof.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(5000, 7000));
                do {
                    continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("gonna see whats good");
                }
                while (Dialogues.canContinue());
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("gonna see whats good");
                }
                while (Dialogues.canContinue());
            }
        }
    }

    @Override
    public boolean accept() {
        return !FreeQuest.ERNEST_THE_CHICKEN.isFinished();
      //this will check if ernest the chicken quest is started.

    }
    @Override
    public int execute(){

        startQuest();
        talkToProf();
        getFishFood();
        getPoison();
        underground();
        levers();
        poisonFood();
        spade();
        key();
        gauge();
        tube();
        finishQuest();

        return 5000000;
    }
}
