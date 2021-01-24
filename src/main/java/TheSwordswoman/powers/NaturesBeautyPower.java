package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.actions.CardTypeFromDeckToHandAction;
import TheSwordswoman.util.TextureLoader;

public class NaturesBeautyPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("NaturesBeautyPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/NB84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/NM32.png");

    public NaturesBeautyPower(final int amount) {
        name = "Nature's Beauty";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public int usedAttackThisTurn = 0;

    @Override
    public void atStartOfTurn() {
        usedAttackThisTurn = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (usedAttackThisTurn < amount) {
            usedAttackThisTurn++;
            flash();
            this.addToBot(new CardTypeFromDeckToHandAction(1, card.type));// 31
        }
    }


    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "The first time you play a card each turn, put a card with its type from your draw pile into your hand.";
        else
            description = "The first #b" + amount + " times you play a card each turn, put a card with its type from your draw pile into your hand.";
    }
}
