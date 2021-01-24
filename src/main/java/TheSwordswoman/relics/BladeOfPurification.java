package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import TheSwordswoman.TheSwordswoman;

public class BladeOfPurification extends AbstractBlazRelic {
    public static final String ID = makeID("BladeOfPurification");

    public BladeOfPurification() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atTurnStart() {
        grayscale = false;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.cost >= 2 && targetCard.type == AbstractCard.CardType.ATTACK && !grayscale) {
            flash();
            grayscale = true;
            atb(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(5, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }
}
