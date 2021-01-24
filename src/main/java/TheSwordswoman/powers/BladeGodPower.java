package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class BladeGodPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("BladeGodPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/BG84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/BG32.png");

    public BladeGodPower(final int amount) {
        name = "Blade God";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        flash();
        addToBot(new GainEnergyAction(amount));
    }

    @Override
    public void updateDescription() {
        description = "Whenever you discard a card, gain #b" + amount + " [E] .";
    }
}
