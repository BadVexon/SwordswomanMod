package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import TheSwordswoman.BlazMod;

public class OpenSpirit extends AbstractBlazCard {

    public final static String ID = makeID("OpenSpirit");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -1;

    public OpenSpirit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        swapStance();
        applyToSelf(new GainStrengthPower(p, 1));
    }

    @Override
    public void trance(AbstractMonster m) {
        applyToSelf(new WeakPower(AbstractDungeon.player, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}