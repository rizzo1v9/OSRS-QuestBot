package src;

import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import src.CooksAssistantTasks.StartQuestTask;
import src.ErnestTheChickenTasks.ErnestTheChickenTasks;
import src.SheepShearerTasks.*;


@ScriptManifest(category = Category.QUEST, name = "Rizzo Quest", description = "Completes quests for ironmen", author = "Rizzo", version = 1.0)
public class QuestBot extends TaskScript{

    @Override
    public void onStart(){
        addNodes(new ErnestTheChickenTasks());
    }
}
