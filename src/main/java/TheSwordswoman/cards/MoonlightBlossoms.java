package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MoonlightBlossoms extends AbstractBlazCard {

    public final static String ID = makeID("MoonlightBlossoms");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 24;
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public MoonlightBlossoms() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        expose(m, 3);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (EnergyPanel.totalCount <= magicNumber) att(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}