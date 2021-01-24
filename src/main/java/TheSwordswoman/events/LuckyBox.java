package TheSwordswoman.events;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;

public class LuckyBox extends AbstractImageEvent {
    public static final String ID = "luckyBox";

    private int screenNum = 0;

    public LuckyBox() {
        super("Lucky Box", "You come across a merchant who looks quite fishy. \"20 Gold for a draw! You can win a relic! \" he says. NL In front of him is a box with only a hole exposed for you to put your hand in. NL \"Care to give it a try?\" He continues, \"If you don't draw one in 30 draws, I'll give you one free of charge!\".", "blazmodResources/images/Event.png");
        this.imageEventText.setDialogOption("Draw [pay 20 Gold, 3% to get a random common relic]");
        this.imageEventText.setDialogOption("[Ignore]");// 38
    }

    int numTries = 0;

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        numTries++;
                        AbstractDungeon.player.loseGold(20);
                        int x = AbstractDungeon.cardRandomRng.random(100);
                        boolean winner = false;
                        if (x <= 3 || numTries == 30) {
                            winner = true;
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2F, Settings.HEIGHT / 2F, AbstractDungeon.returnRandomRelic(AbstractDungeon.returnRandomRelicTier()));
                            if (numTries == 30)
                                this.imageEventText.updateBodyText("\"Wow... a winner is you...\" says the merchant. \"Next customer, please!\"");
                            else
                                this.imageEventText.updateBodyText("\"Wow, a winner is you!\" says the merchant. \"Next customer, please!\"");
                        } else if (AbstractDungeon.player.gold < 20) {
                            this.imageEventText.updateBodyText("\"What a shame! Well, not every try will be a winner!\" NL NL \"Looks like you've played enough for today, sport!\" NL The merchant flashes you a crooked grin. \"Better luck next time!\"");
                        } else {
                            this.imageEventText.updateBodyText("\"What a shame! Well, not every try will be a winner!\"");
                        }
                        this.imageEventText.clearRemainingOptions();
                        int r = 0;
                        if (!winner && AbstractDungeon.player.gold >= 20) {
                            r = 1;
                            this.imageEventText.updateDialogOption(0, "Try again [ Lose #b20 Gold. #b3%: #gGain #ga #gRelic. ]");
                        }
                        if (r == 0) screenNum = 1;
                        this.imageEventText.updateDialogOption(r, "[Leave]");
                        return;
                }
            default:
                this.openMap();
        }

    }
}
