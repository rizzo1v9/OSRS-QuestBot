package src.CooksAssistantTasks;
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

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;

public class StartQuestTask extends TaskNode {
    public enum gameState {
        START_QUEST,
        WALKING_TO_KITCHEN,
        IN_KITCHEN,
        TALK_TO_CHEF,
        GETTING_BUCKET_AND_POT,
        GETTING_WHEAT,
        GETTING_FLOUR,
        GETTING_EGG,
        GETTING_MILK,
        WALKING_BACK_TO_KITCHEN,
        COMPLETING_QUEST

    }
    GroundItem egg = GroundItems.closest("Egg");

    gameState gs;

    CooksAssistantAreas areas = new CooksAssistantAreas();

    public int startQuest(){
        NPC cook;
        MethodProvider.log("Quest not started; starting now");
        if(areas.LumbridgeCastleKitchen.contains(getLocalPlayer().getTile())){
            gs = gameState.IN_KITCHEN;
            MethodProvider.log("Already in Kitchen");
        }
        else {
            gs = gameState.START_QUEST;
        }
        MethodProvider.log(gs);
        if (Quests.isFinished(FreeQuest.COOKS_ASSISTANT)) {
            MethodProvider.log("Quest is finished. Exiting Script.");
            return -1;
        }
        if(gs.equals(gameState.START_QUEST)) {
            gs = gameState.WALKING_TO_KITCHEN;
        }
        MethodProvider.log(gs);
        do {
            walkExact(areas.LumbridgeCastleKitchen.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to kitchen");
        }
        while(gs.equals(gameState.WALKING_TO_KITCHEN) && !areas.LumbridgeCastleKitchen.contains(getLocalPlayer().getTile()));
        gs = gameState.IN_KITCHEN;
        MethodProvider.log(gs);
        cook = NPCs.closest("Cook");
        MethodProvider.log(cook);
        MethodProvider.log("In kitchen");

        if(cook != null && gs.equals(gameState.IN_KITCHEN))
        {
            if(cook.interact("Talk-To")) {
                log("Talking to cook");
                gs = gameState.TALK_TO_CHEF;
                sleep(Calculations.random(3000,4000));
                continueDialogue();
                MethodProvider.log("continued 1st");
                sleep(Calculations.random(3000,4000));
                chooseOption(1);
                MethodProvider.log("continued 2nd");
                sleep(Calculations.random(3000,4000));
                continueDialogue();
                MethodProvider.log("continued 3rd");
                sleep(Calculations.random(3000,4000));
                continueDialogue();
                sleep(Calculations.random(3000,4000));
                MethodProvider.log("continued 4th");
                sleep(Calculations.random(3000,4000));
                continueDialogue();
                MethodProvider.log("continued 5th");
                sleep(Calculations.random(3000,4000));
                chooseOption(1);
                MethodProvider.log("started quest");
                sleep(Calculations.random(3000,4000));
                continueDialogue();
                MethodProvider.log("yes ill help you");
                MethodProvider.log(gs);
            }
        }


        return 10000;
    }

    public int getEgg() {
        gs = gameState.GETTING_EGG;
        do {
            walkExact(areas.ChickenCoop.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to chicken coop");
        }
        while(gs.equals(gameState.GETTING_EGG) && !areas.ChickenCoop.contains(getLocalPlayer().getTile()));
        MethodProvider.log("Plz work getting egg XD");
        if (egg != null) {
            egg.interact("Take");
            sleep(2000, 3000);
        }

        return 10000;
    }

    public int getMilk() {
        gs = gameState.GETTING_MILK;
        GameObject cow;
        do {
            walkExact(areas.CowPen.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to cow pen");
        }
        while (gs.equals(gameState.GETTING_MILK) && !areas.CowPen.contains(getLocalPlayer().getTile()));
        cow = GameObjects.closest("Dairy cow");
        MethodProvider.log(cow);
        if (cow != null && gs.equals(gameState.GETTING_MILK)) {
            if (cow.interact("Milk")) {
                log("Milking");
                sleep(1000, 2000);
            }
        }
        return 10000;
    }

    public int getWheat() {
        gs = gameState.GETTING_WHEAT;
        GameObject wheat;
        do {
            walkExact(areas.WheatField.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to wheat field");
        }
        while (gs.equals(gameState.GETTING_WHEAT) && !areas.WheatField.contains(getLocalPlayer().getTile()));
        wheat = GameObjects.closest("Wheat");
        if (wheat != null && gs.equals(gameState.GETTING_WHEAT)) {
            if (wheat.interact("Pick")) {
                log("Picking wheat");
                sleep(1000, 2000);
            }
        }
        return 10000;
    }

    public int getFlour(){
        gs = gameState.GETTING_FLOUR;
        GameObject ladder;
        GameObject hopper;
        GameObject hopperControls;
        GameObject flourBin;

        do {
            walkExact(areas.WheatMill.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to wheat mill");
        }
        while (gs.equals(gameState.GETTING_FLOUR) && !areas.WheatMill.contains(getLocalPlayer().getTile()));
        ladder = GameObjects.closest("Ladder");

        if(ladder != null && gs.equals(gameState.GETTING_FLOUR)){
            if (ladder.interact("Climb-up")) {
                log("Climbing up first ladder");
                sleep(1500,2000);
            }
        }
        sleep(2000,3000);
        ladder = GameObjects.closest("Ladder");
        if(ladder != null && gs.equals(gameState.GETTING_FLOUR)){
            if (ladder.interact("Climb-up")) {
                log("Climbing up second ladder");
                sleep(1500,2000);
            }
        }
        hopper = GameObjects.closest("Hopper");
        MethodProvider.log(hopper);
        if(hopper != null && gs.equals(gameState.GETTING_FLOUR)){
            if (hopper.interact("Fill")) {
                log("Filling Hopper");
                sleep(1500,2000);
            }
        }
        sleep(3000,4000);
        hopperControls = GameObjects.closest("Hopper controls");
        if(hopperControls != null && gs.equals(gameState.GETTING_FLOUR)){
            if (hopperControls.interact("Operate")) {
                log("Operating controls");
                sleep(1500,2000);
            }
        }
        sleep(2000,3000);
        ladder = GameObjects.closest("Ladder");
        if(ladder != null && gs.equals(gameState.GETTING_FLOUR)){
            if (ladder.interact("Climb-down")) {
                log("Climbing down second ladder");
                sleep(1500,2000);
            }
        }
        sleep(1500,2000);
        ladder = GameObjects.closest("Ladder");
        if(ladder != null && gs.equals(gameState.GETTING_FLOUR)){
            if (ladder.interact("Climb-down")) {
                log("Climbing down first ladder");
                sleep(1500,2000);
            }
        }
        flourBin = GameObjects.closest("Flour bin");
        if(flourBin != null && gs.equals(gameState.GETTING_FLOUR)){
            if (flourBin.interact("Empty")) {
                log("Emptying flour bin");
                sleep(1500,2000);
            }
        }
        sleep(1500,2000);
        return 10000;
    }


    public int getBucketAndPot() {
        gs = gameState.GETTING_BUCKET_AND_POT;
        do {
            walkExact(areas.LumbridgeGeneralStore.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to general Store");
        }
        while(gs.equals(gameState.GETTING_BUCKET_AND_POT) && !areas.LumbridgeGeneralStore.contains(getLocalPlayer().getTile()));
        NPC shopKeeper = NPCs.closest("Shop keeper");
        NPC shopAssistant = NPCs.closest("Shop assistant");

        if(shopKeeper != null && gs.equals(gameState.GETTING_BUCKET_AND_POT)){
            if(shopKeeper.interact("Trade")){
                    sleep(Calculations.random(3000, 4000));
                    MethodProvider.log("buying from shop");
                    sleep(Calculations.random(1000, 2000));
                    Shop.purchaseOne("Bucket");
                    sleep(Calculations.random(1000, 2000));
                    Shop.purchaseOne("Pot");
                    MethodProvider.log("Should be done");
                    Widgets.closeAll();
                    
            }
        }
            MethodProvider.log("Cringey");

        return Calculations.random(3000,4000);
    }

    public int walkBackToKitchen(){
        gs = gameState.WALKING_BACK_TO_KITCHEN;
        do {
            walkExact(areas.LumbridgeCastleKitchen.getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to kitchen");
        }
        while(gs.equals(gameState.WALKING_BACK_TO_KITCHEN) && !areas.LumbridgeCastleKitchen.contains(getLocalPlayer().getTile()));
        gs = gameState.IN_KITCHEN;
        MethodProvider.log(gs);

        MethodProvider.log("In kitchen");
        return 10000;
    }

    public int completeQuest(){
        gs = gameState.COMPLETING_QUEST;
        NPC cook;
        cook = NPCs.closest("Cook");
        MethodProvider.log(cook);
        if(cook != null && gs.equals(gameState.COMPLETING_QUEST)){
            if(cook.interact("Talk-To")){
                log("Talking to cook");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 1st");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 2nd");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 3rd");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 4th");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 5th");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 6th");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 7th");
                sleep(3000,4000);
                continueDialogue();
                MethodProvider.log("continued 8th");
                sleep(3000,4000);
                Widgets.closeAll();
            }
        }
        return 10000;
    }


    @Override
    public boolean accept() {
        return !FreeQuest.COOKS_ASSISTANT.isFinished();
    }

    @Override
    public int execute() {
        startQuest();
        getBucketAndPot();
        getEgg();
        getMilk();
        getWheat();
        getFlour();
        walkBackToKitchen();
        return 1000000;
    }
}
