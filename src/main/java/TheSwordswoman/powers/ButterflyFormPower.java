package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class ButterflyFormPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("ButterflyFormPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/BE84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/BE32.png");

    public ButterflyFormPower() {
        name = "Butterfly Form";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = -1;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = "Your Stance split cards gets both effects.";
    }
}
