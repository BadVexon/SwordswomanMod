package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.ReflectionPower;

public class OneWithNature extends AbstractBlazCard {

    public final static String ID = makeID("OneWithNature");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public OneWithNature() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new WallopAction(m, makeInfo()));
        applyToSelf(new ReflectionPower(1));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}