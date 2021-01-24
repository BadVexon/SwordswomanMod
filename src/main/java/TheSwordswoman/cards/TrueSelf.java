package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class TrueSelf extends AbstractBlazCard {

    public final static String ID = makeID("TrueSelf");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 9;
    private static final int MAGIC = 1;

    public TrueSelf() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isEthereal = true;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToSelf(new IntangiblePlayerPower(p, magicNumber));
    }

    public void upp() {
        isEthereal = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}