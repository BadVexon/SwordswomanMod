package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.patches.EndOfTurnPatch;
import TheSwordswoman.util.TextureLoader;

public class TravelJournalPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("TravelJournalPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/TJ84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/TJ32.png");

    public TravelJournalPower(final int amount) {
        name = "Travel Journal";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (!EndOfTurnPatch.damageTakenLastTurn) {
            flash();
            addToBot(new DrawCardAction(amount));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "At the start of your turn, if no damage was taken last turn, draw #b" + amount + " card.";
        else
            description ="At the start of your turn, if no damage was taken last turn, draw #b" + amount + " cards.";
    }
}
