package TheSwordswoman.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import TheSwordswoman.util.RemoveCardReward;
import TheSwordswoman.util.UpgradeCardReward;

public class PurpleButterfly extends AbstractImageEvent {
    public static final String ID = "purpleButterfly";

    private int screenNum = 0;

    public PurpleButterfly() {
        super("Purple Butterfly", "After travelling for hours in a forest, you can feel your surroundings getting darker. NL You're lost and you have no way to pinpoint yourself. Your only waypoints are your footprints. NL But just then, a purple butterfly floats across your field of view. What will you do?", "blazmodResources/images/Event.png");
        this.imageEventText.setDialogOption("Follow the Butterfly through the thick woods. [ #rLose #r" + AbstractDungeon.player.currentHealth / 10 + " #rHP ]");
        this.imageEventText.setDialogOption("Retrace your steps. [ #rEncounter #rEnemies ]");// 38
        this.noCardsInRewards = true;
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.damage(new DamageInfo(null, AbstractDungeon.player.currentHealth / 10, DamageInfo.DamageType.HP_LOSS));// 114
                        this.imageEventText.updateBodyText("You follow the butterfly posthaste and the branches on your way scrapes your skin. Some made a noticeable cut into you. However, the butterfly leads you into an moonlit opening. It's a serenic view of a beautiful grove.");
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, "Meditate [ #gRemove #b1 #gcard. #gUpgrade #b1 #gcard. ]");
                        this.imageEventText.clearRemainingOptions();
                        return;
                    case 1:
                        this.imageEventText.updateBodyText("Looks like you are not the only one following your footprints. NL As you retrace your steps, you find a Spike Slime blocking your way out. Your only way now is to fight.");// 60
                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Large Slime");
                        this.enterCombatFromImage();
                        return;// 73
                }
                break;
            case 1:
                if (buttonPressed == 0) {
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RemoveCardReward());
                    AbstractDungeon.getCurrRoom().rewards.add(new UpgradeCardReward());
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                    AbstractDungeon.combatRewardScreen.open();
                    screenNum = 2;
                }
                break;
        }

    }
}
