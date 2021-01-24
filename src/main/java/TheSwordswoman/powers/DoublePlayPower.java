package TheSwordswoman.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class DoublePlayPower extends AbstractPower {
    public static final String POWER_ID = BlazMod.makeID("DoublePlayPower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/WB84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/WB32.png");

    public DoublePlayPower(final int amount) {
        name = "Incoming Copy!";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0) {// 46
            this.flash();// 49
            addToBot(new ReducePowerAction(owner, owner, this, 1));
            for (int i = 0; i < 2; i++) {

                AbstractMonster m = null;// 50
                if (action.target != null) {// 52
                    m = (AbstractMonster) action.target;// 53
                }

                AbstractCard tmp = card.makeSameInstanceOf();// 56
                AbstractDungeon.player.limbo.addToBottom(tmp);// 57
                tmp.current_x = card.current_x;// 58
                tmp.current_y = card.current_y;// 59
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;// 60
                tmp.target_y = (float) Settings.HEIGHT / 2.0F;// 61
                if (m != null) {// 63
                    tmp.calculateCardDamage(m);// 64
                }

                tmp.purgeOnUse = true;// 67
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);// 68
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "Copy the next card you play twice.";
        } else
            description = "Copy the next #b" + amount + " cards you play twice.";
    }
}
