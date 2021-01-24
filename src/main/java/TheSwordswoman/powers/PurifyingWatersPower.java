package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class PurifyingWatersPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("PurifyingWatersPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/PW84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/PW32.png");

    public PurifyingWatersPower(final int amount) {
        name = "Purifying Waters";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        flash();
        addToBot(new DrawCardAction(owner, amount));
        addToBot(new ExhaustAction(owner, owner, amount, false, false, false));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you change Stances, draw #b" + amount + " card, then #yExhaust #b" + amount + " card.";
        else
            description = "Whenever you change Stances, draw #b" + amount + " cards, then #yExhaust #b" + amount + " cards.";
    }
}
