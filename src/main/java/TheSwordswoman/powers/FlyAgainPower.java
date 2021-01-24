package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class FlyAgainPower extends AbstractPower implements OnMyBlockBrokenPower {
    public static final String POWER_ID = BlazMod.makeID("FlyAgainPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/FA84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/FA32.png");

    public FlyAgainPower(final int amount) {
        name = "Fly, Fly Again";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public boolean triggeredThisTurn = false;

    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = false;
    }

    @Override
    public void onMyBlockBroken() {
        if (!triggeredThisTurn) {
            triggeredThisTurn = true;
            flash();
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ReflectionPower(amount), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "The first time your #yBlock is broken each turn, gain #b" + amount + " #yReflection.";
    }
}
