package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.BlazMod;

public class FortifiedVigor extends AbstractBlazCard {

    public final static String ID = makeID("FortifiedVigor");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public FortifiedVigor() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF);
        baseDamage = 10;
        isMultiDamage = true;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void trance(AbstractMonster m) {
        exhaust = true;
    }

    public void upp() {
        upgradeDamage(2);
    }
}