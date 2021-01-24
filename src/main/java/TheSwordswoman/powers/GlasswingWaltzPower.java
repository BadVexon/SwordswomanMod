package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class GlasswingWaltzPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("GlasswingWaltzPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/GW84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/GW32.png");

    public GlasswingWaltzPower(final int amount) {
        name = "Glasswing Waltz";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Your next Stance split card gets both effects.";
        else
            description = "Your next #b" + amount + " Stance split cards get both effects.";
    }
}
