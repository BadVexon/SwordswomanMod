package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.actions.RandomStrikeOrDefendDrawPileToHandAction;
import TheSwordswoman.util.TextureLoader;

public class ContingencyPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("ContingencyPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/CP84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/CP32.png");

    public ContingencyPower(final int amount) {
        name = "Contingency";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {// 32
            this.flash();// 33
            this.addToBot(new RandomStrikeOrDefendDrawPileToHandAction(amount));
        }

    }// 36

    @Override
    public void updateDescription() {
        description = "Whenever you deal unblocked attack damage, put #b" + amount + " random #yBasic cards from your draw pile into your hand.";
    }
}
