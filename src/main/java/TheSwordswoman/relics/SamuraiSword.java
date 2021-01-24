package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import TheSwordswoman.TheSwordswoman;

public class SamuraiSword extends AbstractBlazRelic {
    public static final String ID = makeID("SamuraiSword");

    public SamuraiSword() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (!grayscale) {
            return 1;
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.ATTACK) {
            grayscale = true;
            flash();
        }
    }

    @Override
    public void atTurnStart() {
        grayscale = false;
    }
}
