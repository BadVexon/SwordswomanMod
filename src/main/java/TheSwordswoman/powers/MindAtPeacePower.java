package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class MindAtPeacePower extends AbstractPower implements OnLoseBlockPower {
    public static final String POWER_ID = BlazMod.makeID("MindAtPeacePower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/MP84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/Absorb32.png");

    public MindAtPeacePower() {
        name = "Mind at Peace";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = 0;

        type = PowerType.BUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new VigorPower(owner, amount), amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public int onLoseBlock(DamageInfo info, int damageAmount) {
        amount += Math.min(damageAmount, owner.currentBlock);
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, gain #b" + amount + " #yVigor. Amount increased by Block loss.";
    }
}
