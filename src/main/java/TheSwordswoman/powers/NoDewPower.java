package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class NoDewPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("NoDewPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/CtD84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/CtD32.png");

    public NoDewPower(final int amount) {
        name = "No Dew";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));// 41
    }// 42

    @Override
    public void onSpecificTrigger() {
        flash();
        addToBot(new GainBlockAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        description = "This turn, whenever you discard a card, gain #b" + amount + " #yBlock .";
    }
}
