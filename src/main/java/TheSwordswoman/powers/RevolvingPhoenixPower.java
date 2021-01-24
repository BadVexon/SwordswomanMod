package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class RevolvingPhoenixPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("RevolvingPhoenixPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/RP84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/RP32.png");

    public RevolvingPhoenixPower(final int amount) {
        name = "Revolving Phoenix";
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
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        addToBot(new DrawCardAction(amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you change Stance, gain #b" + amount + " #yStrength and draw #b" + amount + " card.";
        else
            description = "Whenever you change Stance, gain #b" + amount + " #yStrength and draw #b" + amount + " cards.";
    }
}
