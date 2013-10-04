package com.gubs.testDrools;

// http://www.commonj.com/blogs/2011/01/28/drools-tutorial-fundamentals/

import java.io.File;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class SimpleRule {

  /**
   * @param args
   */
  public static void main(String[] args) {

    KnowledgeBase knowledgeBase = createKnowledgeBase();

    StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();

    Account account = new Account(1, "Sai", 29, 400);
    Account account2 = new Account(2, "Gubs", 28, 400);
    Account account3 = new Account(3, "Kavit", 27, 400);

    session.insert(account);
    session.insert(account2);
    session.insert(account3);

    session.fireAllRules();

  }

  private static KnowledgeBase createKnowledgeBase() {

    KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    File accountRules = new File("src/main/resources/BasicAccountRule.drl");
    builder.add(ResourceFactory.newFileResource(accountRules), ResourceType.DRL);

    if (builder.hasErrors()) {
      System.out.println("Builder failed to load");
    }

    KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
    knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

    return knowledgeBase;
  }

}
