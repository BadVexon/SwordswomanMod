package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class FleetingDawnPower extends TwoAmountPower {
    public static final String POWER_ID = BlazMod.makeID("FleetingDawnPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/FD84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/FD32.png");

    public FleetingDawnPower(final int amount, final int amount2) {
        name = "Fleeting Dawn";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount2 = amount;
        this.amount = amount2;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (amount2 < amount && card.type == AbstractCard.CardType.ATTACK) {
            amount2++;
            flash();
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1), 1));
        }
    }


    @Override
    public void updateDescription() {
        description = "Gain #b1 #yStrength whenever you play an #yAttack, up to #b" + amount + " total.";
    }
}
