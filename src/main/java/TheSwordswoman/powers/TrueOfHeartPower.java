package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class TrueOfHeartPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("TrueOfHeartPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/ToH84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/ToH32.png");

    public TrueOfHeartPower(final int amount) {
        name = "True of Heart";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public boolean activatedThisTurn = false;

    @Override
    public void atStartOfTurn() {
        activatedThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!activatedThisTurn && card.type == AbstractCard.CardType.ATTACK) {
            activatedThisTurn = true;
            flash();
            addToBot(new ApplyPowerAction(owner, owner, new MetallicizePower(owner, amount), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "The first time you play an #yAttack each turn, gain #b" + amount + " #yMetallicize.";
    }
}
