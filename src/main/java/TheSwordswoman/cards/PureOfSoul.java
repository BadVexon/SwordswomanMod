package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import TheSwordswoman.BlazMod;

public class PureOfSoul extends AbstractBlazCard {

    public final static String ID = makeID("PureOfSoul");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public PureOfSoul() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        weaken(m, magicNumber);
        expose(m, magicNumber);
    }

    @Override
    public void trance(AbstractMonster m) {
        applyToSelf(new WeakPower(AbstractDungeon.player, magicNumber, false));
        applyToSelf(new VulnerablePower(AbstractDungeon.player, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBaseCost(1);
    }
}