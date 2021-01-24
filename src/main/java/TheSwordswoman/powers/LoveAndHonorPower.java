package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class LoveAndHonorPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("LoveAndHonorPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/LaH84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/LaH32.png");

    public LoveAndHonorPower(final int amount) {
        name = "Love and Honor";
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
        addToBot(new GainBlockAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        description = "Whenever you change Stances, gain #b" + amount + " #yBlock.";
    }
}
