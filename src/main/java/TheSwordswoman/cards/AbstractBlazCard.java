package TheSwordswoman.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.TheSwordswoman;
import TheSwordswoman.powers.ButterflyFormPower;
import TheSwordswoman.powers.GlasswingWaltzPower;
import TheSwordswoman.stances.DawnflyStance;
import TheSwordswoman.stances.FreeflutterStance;

import java.util.ArrayList;

import static TheSwordswoman.BlazMod.*;

public abstract class AbstractBlazCard extends CustomCard {

    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    public int silly;
    public int baseSilly;
    public boolean upgradedSilly;
    public boolean isSillyModified;

    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    private boolean doTrance = false;

    public AbstractBlazCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, "ERROR", getCorrectPlaceholderImage(type, id),
                cost, "ERROR", type, TheSwordswoman.Enums.SWORDSWOMAN_COLOR, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    public AbstractBlazCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id, "ERROR", getCorrectPlaceholderImage(type, id),
                cost, "ERROR", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    public static String getCorrectPlaceholderImage(CardType type, String id) {
        String img = makeCardPath(id.replaceAll((getModID() + ":"), "") + ".png");
        if ((!Gdx.files.internal(img).exists()))
            switch (type) {
                case ATTACK:
                    return makeCardPath("Attack.png");
                case SKILL:
                    return makeCardPath("Skill.png");
                case POWER:
                    return makeCardPath("Power.png");
            }
        return img;
    }

    public static String makeID(String blah) {
        return getModID() + ":" + blah;
    }

    private boolean useStuff = false;
    AbstractCard qHoverOne;
    AbstractCard qHoverTwo;

    public void use(AbstractPlayer p, AbstractMonster m) {
        us(p, m);
        if (doTrance) {
            trance(m);
            AbstractCard q = this;
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (AbstractDungeon.player.exhaustPile.contains(q)) {
                        AbstractDungeon.player.hand.removeCard(q);
                        AbstractDungeon.player.discardPile.removeCard(q);
                        AbstractDungeon.player.drawPile.removeCard(q);
                        AbstractDungeon.player.exhaustPile.removeCard(q);
                    } else {
                        AbstractDungeon.player.hand.removeCard(q);
                        AbstractDungeon.player.exhaustPile.removeCard(q);
                        AbstractDungeon.player.drawPile.removeCard(q);
                        AbstractDungeon.player.discardPile.removeCard(q);
                    }
                }
            });
            doTrance = false;
        }
    }

    public abstract void us(AbstractPlayer p, AbstractMonster m);

    @Override
    public void applyPowers() {
        if (rawDescription.contains("NL π")) {
            String[] s = rawDescription.split("NL π");
            qHoverOne = makeStatEquivalentCopy();
            qHoverOne.rawDescription = s[0];
            qHoverOne.name = EXTENDED_DESCRIPTION[0] + (upgraded ? "+" : "");
            qHoverOne.initializeDescription();
            qHoverTwo = makeStatEquivalentCopy();
            qHoverTwo.rawDescription = s[1];
            qHoverTwo.name = EXTENDED_DESCRIPTION[1] + (upgraded ? "+" : "");
            qHoverTwo.initializeDescription();
            if (qHoverTwo instanceof CustomCard)
                ((CustomCard) qHoverTwo).loadCardImage(getCorrectPlaceholderImage(type, cardID + "B"));
            useStuff = true;
        }
        if (useStuff) {
            if (AbstractDungeon.player.hasPower(ButterflyFormPower.POWER_ID) || AbstractDungeon.player.hasPower(GlasswingWaltzPower.POWER_ID)) {
                rawDescription = upgraded ? EXTENDED_DESCRIPTION[3] : EXTENDED_DESCRIPTION[2];
                name = EXTENDED_DESCRIPTION[4] + (upgraded ? "+" : "");
                initializeDescription();
                cardsToPreview = null;
            } else if (AbstractDungeon.player.stance.ID.equals(DawnflyStance.STANCE_ID)) {
                rawDescription = qHoverOne.rawDescription;
                name = qHoverOne.name;
                loadCardImage(getCorrectPlaceholderImage(type, cardID));
                initializeDescription();
                cardsToPreview = qHoverTwo;
            } else if (AbstractDungeon.player.stance.ID.equals(FreeflutterStance.STANCE_ID)) {
                rawDescription = qHoverTwo.rawDescription;
                name = qHoverTwo.name;
                loadCardImage(getCorrectPlaceholderImage(type, cardID + "B"));
                initializeDescription();
                cardsToPreview = qHoverOne;
            }
        }

        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.calculateCardDamage(mo);
    }

    public void altdmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), fx));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        if (this.hasTag(BlazMod.TRANCE)) {
            freeToPlayOnce = true;
            doTrance = true;
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            atb(new NewQueueCardAction(this, m));
        }
    }

    public void trance(AbstractMonster m) {

    }

    public abstract void upp();

    protected void atb(AbstractGameAction action) {
        addToBot(action);
    }

    protected void att(AbstractGameAction action) {
        addToTop(action);
    }

    protected DamageInfo makeInfo() {
        return makeInfo(damageTypeForTurn);
    }

    private DamageInfo makeInfo(DamageInfo.DamageType type) {
        return new DamageInfo(AbstractDungeon.player, damage, type);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, makeInfo(), fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    public void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public void makeInHand(AbstractCard c, int i) {
        atb(new MakeTempCardInHandAction(c, i));
    }

    public void makeInHand(AbstractCard c) {
        makeInHand(c, 1);
    }

    void shuffleIn(AbstractCard c, int i) {
        atb(new MakeTempCardInDrawPileAction(c, i, false, true));
    }

    public void shuffleIn(AbstractCard c) {
        shuffleIn(c, 1);
    }

    public void topDeck(AbstractCard c, int i) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, i, false, true));
    }

    public void topDeck(AbstractCard c) {
        topDeck(c, 1);
    }

    public ArrayList<AbstractMonster> monsterList() {
        ArrayList<AbstractMonster> monsters = new ArrayList<>(AbstractDungeon.getMonsters().monsters);
        monsters.removeIf(AbstractCreature::isDeadOrEscaped);
        return monsters;
    }

    public void applyToEnemy(AbstractMonster m, AbstractPower po) {
        atb(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    WeakPower autoWeak(AbstractMonster m, int i) {
        return new WeakPower(m, i, false);
    }

    VulnerablePower autoVuln(AbstractMonster m, int i) {
        return new VulnerablePower(m, i, false);
    }

    public void weaken(AbstractMonster m, int i) {
        applyToEnemy(m, autoWeak(m, i));
    }

    public void expose(AbstractMonster m, int i) {
        applyToEnemy(m, autoVuln(m, i));
    }

    public void swapStance() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.player.stance instanceof DawnflyStance) {
                    att(new ChangeStanceAction(FreeflutterStance.STANCE_ID));
                } else if (AbstractDungeon.player.stance instanceof FreeflutterStance) {
                    att(new ChangeStanceAction(DawnflyStance.STANCE_ID));
                }
            }
        });
    }

    public void stanceCheck(AbstractMonster target) {
        if (AbstractDungeon.player.hasPower(GlasswingWaltzPower.POWER_ID) || AbstractDungeon.player.hasPower(ButterflyFormPower.POWER_ID)) {
            abc(target);
        } else if (AbstractDungeon.player.stance instanceof DawnflyStance) {
            dawnfly(target);
        } else if (AbstractDungeon.player.stance instanceof FreeflutterStance) {
            freeflutter(target);
        }
    }

    public void abc(AbstractMonster m) {

    }

    public int reverb() {
        int x = 0;
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (q.cardID.equals(this.cardID))
                x++;
        }
        return x - 1;
    }

    public void dawnfly(AbstractMonster m) {

    }

    public void freeflutter(AbstractMonster m) {

    }

    public void resetAttributes() {
        super.resetAttributes();
        silly = baseSilly;
        isSillyModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSilly) {
            silly = baseSilly;
            isSillyModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    void upgradeSilly(int amount) {
        baseSilly += amount;
        silly = baseSilly;
        upgradedSilly = true;
    }

    void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }
}