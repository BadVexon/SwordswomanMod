package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class BlindBlade extends AbstractBlazCard {

    public final static String ID = makeID("BlindBlade");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 4;

    public BlindBlade() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        baseSilly = silly = 3;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VigorPower(p, magicNumber));
        applyToSelf(new StrengthPower(p, silly));
        applyToSelf(new EntanglePower(p));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeSilly(2);
    }
}