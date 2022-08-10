package src.RomeoAndJulietTasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;

public class RomeoAndJulietTask extends TaskNode {

    public enum gameState{
        START_QUEST, TALKING_TO_JULIET, TALKING_TO_ROMEO, TALKING_TO_FATHER, TALKING_TO_APOTHECARY, TALKING_TO_ROMEO2, GETTING_CADAVA_BERRIES, TALKING_TO_APOTHECARY2, FINISHING_QUEST,

    }
    RomeoAndJulietAreas areas = new RomeoAndJulietAreas();

    gameState gs;
    NPC romeo;
    NPC juliet;
    NPC father;
    NPC apothecary;
    GameObject cadavaBerryBush;

    public boolean isInCutscene() {
        return Tabs.isDisabled(Tab.INVENTORY);
    }

    public void startQuest(){
        gs = gameState.START_QUEST;
        do{
            walkExact(areas.VarrockFountain.getRandomTile());
            sleep(1000,2000);
            MethodProvider.log("Walking to fountain");
        }
        while(!areas.VarrockFountain.contains(getLocalPlayer().getTile()));
        romeo = NPCs.closest("Romeo");
        if(romeo != null){
            if(romeo.interact("Talk-to")){
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000,8000));
                continueDialogue();
                sleep(1000,2000);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(1500,2500);
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(1500,2500);
                chooseOption(3);

            }

        }
    }

    public void talkToJuliet() {
        gs = gameState.TALKING_TO_JULIET;
        do{
            walkExact(areas.JulietTile);
            sleep(1000,1250);
            MethodProvider.log("Walking to Juliet");
        }
        while(!getLocalPlayer().getTile().equals(areas.JulietTile));
        juliet = NPCs.closest("Juliet");
        if(juliet != null){
            if(juliet.interact("Talk-to")){
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(1000,2000);
                chooseOption(4);
            }
        }
    }



    public void talkToRomeo() {
        gs = gameState.TALKING_TO_ROMEO;
        do{
            walkExact(areas.VarrockFountain.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to fountain");
        }
        while(!areas.VarrockFountain.contains(getLocalPlayer().getTile()));
        romeo = NPCs.closest("Romeo");
        if(romeo != null) {
            if (romeo.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                }
            }
        }

    public void talkToFatherLawrence() {
        gs = gameState.TALKING_TO_FATHER;
        do{
            walkExact(areas.VarrockEastChurch.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to Father Lawrence");
        }
        while(!areas.VarrockEastChurch.contains(getLocalPlayer().getTile()));
//      sleepUntil(() -> !isInCutscene(), Calculations.random(7000,8000));
        father = NPCs.closest("Father Lawrence");
        if(father != null){
            if(father.interact("Talk-to")){
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleepUntil(() -> Dialogues.canContinue(), Calculations.random(7000,8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
            }
        }
    }

    public void talkToRomeo2() {
        gs = gameState.TALKING_TO_ROMEO2;
        do{
            walkExact(areas.VarrockFountain.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to fountain");
        }
        while(!areas.VarrockFountain.contains(getLocalPlayer().getTile()));
        romeo = NPCs.closest("Romeo");
        if(romeo != null) {
            if (romeo.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(Calculations.random(1750, 2000));
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(Calculations.random(1750, 2000));
                chooseOption(3);
            }
        }
    }

    public void talkToApothecary() {
        gs = gameState.TALKING_TO_APOTHECARY;
        do{
            walkExact(areas.VarrockApothecary.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to Apothecary");
        }
        while(!areas.VarrockApothecary.contains(getLocalPlayer().getTile()));
        apothecary = NPCs.closest("Apothecary");
        if(apothecary != null){
            if(apothecary.interact("Talk-to")){
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                continueDialogue();
                sleep(Calculations.random(1750, 2000));
                chooseOption(2);
                sleep(Calculations.random(1750, 2000));
                chooseOption(1);
                sleep(Calculations.random(1750, 2000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1000, 1250));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleep(Calculations.random(1750, 2000));
                continueDialogue();

            }
        }
    }

    public void getCadavaBerries() {
        gs = gameState.GETTING_CADAVA_BERRIES;
        do{
            walkExact(areas.VarrockCadavaBerries.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to berries");
        }
        while(!areas.VarrockCadavaBerries.contains(getLocalPlayer().getTile()));
        cadavaBerryBush = GameObjects.closest("Cadava Bush");
        if(cadavaBerryBush != null){
            if(cadavaBerryBush.interact("Pick-from")){
                sleepUntil(() -> Inventory.contains("Cadava Berries"), Calculations.random(7000,8000));
            }
        }
    }

    public void talkToApothecary2() {
        gs = gameState.TALKING_TO_APOTHECARY2;
        do{
            walkExact(areas.VarrockApothecary.getRandomTile());
            sleep(1000,1250);
            MethodProvider.log("Walking to Apothecary 2nd time");
        }
        while(!areas.VarrockApothecary.contains(getLocalPlayer().getTile()));
        apothecary = NPCs.closest("Apothecary");
        if(apothecary != null){
            if(apothecary.interact("Talk-to")){
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                continueDialogue();
                sleep(Calculations.random(1500, 2000));
                chooseOption(2);
                sleep(Calculations.random(1500, 2000));
                chooseOption(1);
                do {
                    continueDialogue();
                    sleep(Calculations.random(1000, 1250));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
                sleepUntil(() -> Dialogues.canContinue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1000, 1250));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
            }
        }
    }

    public void talkToJuliet2() {
        gs = gameState.TALKING_TO_JULIET;
        do {
            walkExact(areas.JulietTile);
            sleep(1000, 1250);
            MethodProvider.log("Walking to Juliet");
        }
        while (!getLocalPlayer().getTile().equals(areas.JulietTile));
        juliet = NPCs.closest("Juliet");
        if (juliet != null) {
            if (juliet.interact("Talk-to")) {
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());

            }
            MethodProvider.log(Tabs.getOpen());
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                    sleepUntil(() -> Dialogues.canContinue(), Calculations.random(7000, 8000));
                }
                while (Dialogues.canContinue() && Tabs.getOpen() == null);
//                do {
//                    continueDialogue();
//                    sleep(Calculations.random(1500, 2000));
//                    MethodProvider.log("epic working");
//                }
//                while (Dialogues.canContinue());
//                sleepUntil(() -> Dialogues.canContinue(), Calculations.random(7000, 8000));
//                do {
//                    continueDialogue();
//                    sleep(Calculations.random(1500, 2000));
//                    MethodProvider.log("epic working");
//                }
//                while (Dialogues.canContinue());
//                sleepUntil(() -> Dialogues.canContinue(), Calculations.random(7000, 8000));
//                do {
//                    continueDialogue();
//                    sleep(Calculations.random(1500, 2000));
//                    MethodProvider.log("epic working");
//                }
//                while (Dialogues.canContinue());

        }
    }

    public void finishQuest() {
        gs = gameState.FINISHING_QUEST;
        do {
            walkExact(areas.VarrockFountain.getRandomTile());
            sleep(1000, 1250);
            MethodProvider.log("Walking to fountain");
        }
        while (!areas.VarrockFountain.contains(getLocalPlayer().getTile()));
        romeo = NPCs.closest("Romeo");
        if (romeo != null) {
            if (romeo.interact("Talk-to")) {
                sleepUntil(() -> Dialogues.inDialogue(), Calculations.random(7000, 8000));
                do {
                    continueDialogue();
                    sleep(Calculations.random(1500, 2000));
                    MethodProvider.log("epic working");
                }
                while (Dialogues.canContinue());
            }
        }
        do {
            continueDialogue();
            sleep(Calculations.random(1500, 2000));
            MethodProvider.log("beast working");
            sleepUntil(() -> Dialogues.canContinue(), Calculations.random(15000, 20000));
        }
        while (Dialogues.canContinue() && Tabs.getOpen() == null);
        MethodProvider.log("cringe");

//        sleepUntil(() -> Tabs.getOpen() == null, Calculations.random(7000, 8000));
//        if (Tabs.getOpen() == null) {
//            do {
//                continueDialogue();
//                sleep(Calculations.random(1500, 2000));
//                MethodProvider.log("epic working");
//            }
//            while (Dialogues.canContinue());
//        }
//        sleepUntil(() -> canContinue(), Calculations.random(7000, 8000));
//        if (Tabs.getOpen() == null) {
//            do {
//                continueDialogue();
//                sleep(Calculations.random(1500, 2000));
//                MethodProvider.log("epic working");
//            }
//            while (Dialogues.canContinue());
//        }
//        sleepUntil(() -> canContinue(), Calculations.random(7000, 8000));
//        if (Tabs.getOpen() == null) {
//            do {
//                continueDialogue();
//                sleep(Calculations.random(1500, 2000));
//                MethodProvider.log("epic working");
//            }
//            while (Dialogues.canContinue());
        }



    @Override
    public boolean accept() {
        return !FreeQuest.ROMEO_AND_JULIET.isFinished();
    }

    @Override
    public int execute() {
        startQuest();
        talkToJuliet();
        talkToRomeo();
        talkToFatherLawrence();
        talkToRomeo2();
        talkToApothecary();
        getCadavaBerries();
        talkToApothecary2();
        talkToJuliet2();
        finishQuest();


        return 1000000;
    }
}
