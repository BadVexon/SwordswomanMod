package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class QuickBlade extends AbstractBlazCard {

    public final static String ID = makeID("QuickBlade");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;

    public QuickBlade() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = MAGIC;
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            allDmg(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;// 65
    }

    public void upp() {
        upgradeDamage(UPG_MAGIC);
    }
}