package TheSwordswoman.events;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import TheSwordswoman.util.RemoveCardReward;

public class Grindstone extends AbstractImageEvent {
    public static final String ID = "grindstone";

    private int screenNum = 0;

    public Grindstone() {
        super("Grindstone", "As you venture further into the Spire, you find that your weapon starts to blunt. NL You can take your time to find a grindstone around to sharpen your weapon or you can carry on your journey.", "blazmodResources/images/Event.png");
        this.imageEventText.setDialogOption("Look around to find a grindstone. [Remove 1 card, 50% Enemy encounter]");
        this.imageEventText.setDialogOption("[Continue on]");
        noCardsInRewards = true;
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        int x = AbstractDungeon.cardRandomRng.random(100);
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewards.add(new RemoveCardReward());
                        if (x < 50) {
                            AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("Gremlin Nob");
                            this.enterCombatFromImage();
                        } else {
                            this.imageEventText.updateBodyText("What luck! You found a Grindstone just lying about. NL After sharpening your weapons, your slices feel lighter. However, you can't stop to feel that this could have ended up a lot worse.");
                            AbstractDungeon.combatRewardScreen.open();
                            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                            screenNum = 2;
                            this.imageEventText.updateDialogOption(0, "[Leave]");
                            this.imageEventText.clearRemainingOptions();
                        }
                        break;
                    case 1:
                        screenNum = 1;
                        this.imageEventText.updateBodyText("You feel that there is no need to resharpen your weapon yet, and you continue on in the Spire.");
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, "[Leave]");
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            default:
                this.openMap();
        }

    }
}
