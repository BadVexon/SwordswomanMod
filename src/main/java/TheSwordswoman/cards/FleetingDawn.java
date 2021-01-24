package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.powers.FleetingDawnPower;

public class FleetingDawn extends AbstractBlazCard {

    public final static String ID = makeID("FleetingDawn");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public FleetingDawn() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FleetingDawnPower(0, magicNumber));
    }

    @Override
    public void trance(AbstractMonster m) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -2), -2));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}