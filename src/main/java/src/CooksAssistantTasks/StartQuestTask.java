package src.CooksAssistantTasks;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;

public class StartQuestTask extends TaskNode {
    public enum gameState {
        START_QUEST,
        WALKING_TO_KITCHEN,
        IN_KITCHEN,
        TALK_TO_CHEF,
        GETTING_BUCKET_AND_POT,
        GETTING_FLOUR,
        GETTING_EGG,
        GETTING_MILK

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
       
        if (egg != null) {
            egg.interact("Take");
            sleep(2000, 3000);
        }

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

                    MethodProvider.log("buying from shop");
                    // sleep(Calculations.random(1000, 2000));
                    Shop.purchaseOne("Bucket");
                    sleep(Calculations.random(1000, 2000));
                    Shop.purchaseOne("Pot");


                MethodProvider.log("Not in if");

            }
        }


        return Calculations.random(3000,4000);
    }

    @Override
    public boolean accept() {
        return !FreeQuest.COOKS_ASSISTANT.isFinished();
    }

    @Override
    public int execute() {
        // startQuest();
        if(Inventory.count("Coins") < 3 && ((!Inventory.contains("Bucket")  || !Inventory.contains("Pot")))){
            MethodProvider.log("Not enough coins, going to bank now.");
            if (Bank.openClosest()) {
                MethodProvider.log("Withdrawing 3 coins");
                Bank.withdraw("Coins", 3);
            } else {
                MethodProvider.log("Walking to bank");
                return 300;
            }
        }
        getBucketAndPot();
        // getEgg();
        return 10000;
    }
}
