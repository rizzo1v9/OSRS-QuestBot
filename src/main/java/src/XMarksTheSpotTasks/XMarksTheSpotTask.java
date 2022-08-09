package src.XMarksTheSpotTasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;
import src.CooksAssistantTasks.StartQuestTask;

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walk;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;

public class XMarksTheSpotTask extends TaskNode {


    public enum gameState{
        START_QUEST, TALK_TO_VEOS, WALK_TO_STORE, BUY_SPADE, FIRST_CLUE, SECOND_CLUE, THIRD_CLUE, FOURTH_CLUE, FINISHING_QUEST,
    }

    XMarksTheSpotAreas areas = new XMarksTheSpotAreas();
    gameState gs;
    NPC veos;
    Tile firstClueTile = new Tile(3230, 3209);
    Tile secondClueTile = new Tile(3203, 3212);
    Tile thirdClueTile = new Tile(3108, 3264);
    Tile fourthClueTile = new Tile(3077, 3260);

    public void startQuest(){
        gs = gameState.START_QUEST;
        do{
            walkExact(areas.LumbridgePub.getRandomTile());
            MethodProvider.log("Walking to pub");
            sleep(Calculations.random(1000,2000));
        }
        while(!areas.LumbridgePub.contains(getLocalPlayer().getTile()));
        veos = NPCs.closest("Veos");
        if(veos != null){
            if(veos.interact("Talk-to")){
                gs = XMarksTheSpotTask.gameState.TALK_TO_VEOS;
                sleep(Calculations.random(2000,3000));
                continueDialogue();
                MethodProvider.log("continued 1st");
                sleep(Calculations.random(2000,3000));
                chooseOption(2);
                MethodProvider.log("continued 2nd");
                do{
                    continueDialogue();
                    sleep(Calculations.random(2000,3000));
                    MethodProvider.log("epic working");
                }
                while(Dialogues.canContinue());
                chooseOption(1);
                MethodProvider.log("started");
                do{
                    continueDialogue();
                    sleep(Calculations.random(2000,3000));
                    MethodProvider.log("epic working");
                }
                while(Dialogues.canContinue());
            }
        }
    }

    public void buySpade() {
        gs = gameState.WALK_TO_STORE;
        do{
            walkExact(areas.LumbridgeGeneralStore.getRandomTile());
            sleep(1000,2000);
            MethodProvider.log("Walking to store");
        }
        while(!areas.LumbridgeGeneralStore.contains(getLocalPlayer().getTile()));
        gs = gameState.BUY_SPADE;
        NPC shopKeeper = NPCs.closest("Shop keeper");
        if(shopKeeper != null && gs.equals(gameState.BUY_SPADE)){
            if(shopKeeper.interact("Trade")){
                sleep(Calculations.random(3000, 4000));
                MethodProvider.log("buying from shop");
                sleep(Calculations.random(1000, 2000));
                Shop.purchaseOne("Spade");
                sleep(Calculations.random(500,1000));
                MethodProvider.log("Should be done");
                Widgets.closeAll();

            }
        }
    }

    public void firstClue() {
        gs = gameState.FIRST_CLUE;
        do{
            walkExact(firstClueTile);
            sleep(1000,2000);
            MethodProvider.log("Walking to first clue");
        }
        while(!getLocalPlayer().getTile().equals(firstClueTile));

        if(Inventory.contains("Spade")){
            Inventory.interact("Spade", "Dig");
            MethodProvider.log("Digging");
        }
        sleep(2000,3000);
        continueDialogue();
    }

    public void secondClue() {
        gs = gameState.SECOND_CLUE;
        do{
            walkExact(secondClueTile);
            sleep(1000,2000);
            MethodProvider.log("Walking to second clue");
        }
        while(!getLocalPlayer().getTile().equals(secondClueTile));

        if(Inventory.contains("Spade")){
            Inventory.interact("Spade", "Dig");
            MethodProvider.log("Digging");
        }
        sleep(2000,3000);
        continueDialogue();
    }

    public void thirdClue() {
        gs = gameState.THIRD_CLUE;
        do{
            walkExact(thirdClueTile);
            sleep(1000,2000);
            MethodProvider.log("Walking to third clue");
        }
        while(!getLocalPlayer().getTile().equals(thirdClueTile));

        if(Inventory.contains("Spade")){
            Inventory.interact("Spade", "Dig");
            MethodProvider.log("Digging");
        }
        sleep(2000,3000);
        continueDialogue();
    }

    public void fourthClue() {
        gs = gameState.FOURTH_CLUE;
        do{
            walkExact(fourthClueTile);
            sleep(1000,2000);
            MethodProvider.log("Walking to fourth clue");
        }
        while(!getLocalPlayer().getTile().equals(fourthClueTile));

        if(Inventory.contains("Spade")){
            Inventory.interact("Spade", "Dig");
            MethodProvider.log("Digging");
        }
        sleep(2000,3000);
        do{
            continueDialogue();
            sleep(Calculations.random(2000,3000));
            MethodProvider.log("epic working");
        }
        while(Dialogues.canContinue());
    }

    public void finishQuest() {
        gs = gameState.FINISHING_QUEST;
        do {
            walkExact(areas.PortSarimDock.getRandomTile());
            sleep(1000, 2000);
            MethodProvider.log("Walking to store");
        }
        while (!areas.PortSarimDock.contains(getLocalPlayer().getTile()));
        veos = NPCs.closest("Veos");
        if (veos != null) {
            if (veos.interact("Talk-to")) {
                sleep(Calculations.random(2000, 3000));
//                continueDialogue();
//                MethodProvider.log("continued 1st");
//                sleep(Calculations.random(2000,3000));
//                chooseOption(2);
//                MethodProvider.log("continued 2nd");
                do {
                    continueDialogue();
                    sleep(Calculations.random(2000, 3000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
            }
        }
    }

    @Override
    public boolean accept() {
        return !FreeQuest.X_MARKS_THE_SPOT.isFinished();
    }

    @Override
    public int execute() {
        startQuest();
        buySpade();
        firstClue();
        secondClue();
        thirdClue();
        fourthClue();
        finishQuest();

        return 10000000;
    }
}
