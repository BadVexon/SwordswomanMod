package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DanceOfTheGods extends AbstractBlazCard {

    public final static String ID = makeID("DanceOfTheGods");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public DanceOfTheGods() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -1));
        for (int i = 0; i < reverb(); i++) {
            applyToSelf(new StrengthPower(p, 1));
        }
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}