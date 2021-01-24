package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class NextTurnBufferPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("NextTurnBufferPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/LS84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/LS32.png");

    public NextTurnBufferPower(final int amount) {
        name = "Next Turn Buffer";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new ApplyPowerAction(owner, owner, new BufferPower(owner, amount), amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));// 41
    }// 42

    @Override
    public void updateDescription() {
        description = "At the start of your next turn, gain #b" + amount + " #yBuffer.";
    }
}
