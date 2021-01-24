package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BoulderFlash extends AbstractBlazCard {

    public final static String ID = makeID("BoulderFlash");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    public BoulderFlash() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new WallopAction(m, makeInfo()));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}