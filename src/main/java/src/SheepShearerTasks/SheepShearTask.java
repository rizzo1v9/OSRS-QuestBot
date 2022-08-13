package src.SheepShearerTasks;

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

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walk;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;


public class SheepShearTask extends TaskNode {

    //Created by JoshTach

    SheepShearerAreas area = new SheepShearerAreas();

    NPC fredTheFarmer;
    NPC sheep;
    NPC shopKeeper;
    GameObject wheel;


    public void startQuest() {
        //starts quest with fredthefarmer

        do {
            walkExact(area.FredArea.getRandomTile());
            sleep(1500, 2000);
            MethodProvider.log("walking to fred");

        }
        while (!area.FredArea.contains(getLocalPlayer().getTile()));
        //walks us to fred

        //this will start the quest and go through the entire dialogue with fred.
        fredTheFarmer = NPCs.closest("Fred the Farmer");
        if (fredTheFarmer != null) {
            if (fredTheFarmer.interact("Talk-To")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(8000, 10000));
                do {
                    continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("kickin it with fred");
                }
                while (Dialogues.canContinue());
                sleep(2000, 3000);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("fred asking for too much rn");
                }
                while (Dialogues.canContinue());
                sleep(2000, 3000);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("fred alright ill help him out");
                }
                while (Dialogues.canContinue());
                sleep(2000, 3000);
            }
        }
    }


    //this method will get coins from your bank to buy shears for quest.
    //this method as well as getShears is slightly outdated as you tend to skip now the fred hands you shears for free.
    public void getGP() {
        if (Inventory.count("Coins") < 1) {
            if (Bank.openClosest()) {
                Bank.withdraw("Coins", 1);
                MethodProvider.log("Grabbing moneys");
            } else {
                MethodProvider.log("walking to bank");
            }
        }
    }

    //this method will take us to store and buy shears for quest. :^)
    //this method is outdated see above for reason.
    public void getShears() {
        do {
            walkExact(area.LumbridgeGeneralStore.getRandomTile());
            sleep(1500, 2000);
            MethodProvider.log("walkin to the sto");
        }
        while (area.LumbridgeGeneralStore.contains(getLocalPlayer().getTile()));
        shopKeeper = NPCs.closest("Shop keeper");
        if (shopKeeper != null) {
            if (shopKeeper.interact("Trade")) {
                sleepUntil(() -> Shop.isOpen(), Calculations.random(5000, 8000));
                sleep(1000, 1250);
                Shop.purchaseOne("Shears");
                MethodProvider.log("bought shears");
                sleep(1000, 1250);
                Widgets.closeAll();
            }
        }
    }

    //this method will go to sheep pen and start to shear 20 sheep.
    public void shearSheep() {
        do {
            walkExact(area.SheepArea.getRandomTile());
            sleep(1500, 2000);
            MethodProvider.log("walking to sheep area");
        }
        while (!area.SheepArea.contains(getLocalPlayer().getTile()));

        do {
            //this will look for closest sheep in our area and shear.
            sheep = NPCs.closest("Sheep");
            if (sheep != null) {
                if (sheep.interact("Shear")) {
                    sleep(3000, 5000);
                    MethodProvider.log("shearing sheep");
                }
            }
        }
        while (Inventory.count("Wool") < 20);
    }



    //this method will walk us to the spinning wheel and spin our 20 wool. it also will be able to skip past a level up dialog so no worries.
    public void spinWool() {
        do {
            walkExact(area.SpinningArea.getRandomTile());
            sleep(1500, 2000);
            MethodProvider.log("walking to spinning area");
        }
        while (!area.SpinningArea.contains(getLocalPlayer()));

        wheel = GameObjects.closest("Spinning wheel");
        do {
           if (wheel != null) {
                if (Dialogues.inDialogue()) {
                    Dialogues.clickContinue();
                    //skips dialog here
                }
                 wheel.interact("Spin");
                 sleep(5000, 7000);
                 Keyboard.type(" ", false);
                 MethodProvider.log(Inventory.count("Shears"));
                 // checks if in dialog for level up OR checks if you have 20 wool to avoid constant clicking of wheel.
                 sleepUntil(()-> Dialogues.canContinue() || Inventory.count("Ball of wool") == 20,Calculations.random(45000,55000));
            }
        }
        while(Inventory.count("Ball of wool") != 20);
        //done when you dont have 20 balls of wool
  }


        //this method will finish the quest for us by talking to fred and giving him wool
    public void finishQuest() {
        do {
            walkExact(area.FredArea.getRandomTile());
            sleep(1500, 2000);
            MethodProvider.log("bouta hook my boy up");
        }
        while (!area.FredArea.contains(getLocalPlayer().getTile()));
        fredTheFarmer = NPCs.closest("Fred the Farmer");
        if (fredTheFarmer != null) {
            if (fredTheFarmer.interact("Talk-to")) {
                sleep(2000, 3000);
                MethodProvider.log("talking to the dude");
                Dialogues.continueDialogue();
                sleep(2000, 3000);
                Dialogues.chooseOption(1);
                sleep(2000, 3000);
                do {
                    Dialogues.continueDialogue();
                    sleep(2000, 3000);
                    MethodProvider.log("talking to the dude again");
                }
                while (Dialogues.canContinue());
            }
        }
    }

    @Override
    public boolean accept() {
        return !FreeQuest.SHEEP_SHEARER.isFinished();
        //checks if you have quest completed or not.
    }

    @Override
    public int execute(){

        startQuest();
        getGP();
        getShears();
        shearSheep();
        spinWool();
        finishQuest();

        return 5000000;
    }




























}
