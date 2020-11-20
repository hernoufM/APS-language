package aps0.ast.declaration;

import aps0.ast.AstCommand;

/**
 * AstDeclaration
 */
public abstract class AstDeclaration extends AstCommand {
    private String name;

    public AstDeclaration(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    } 

}