package dsl

class EmailDsl {
    String toText
    String fromText
    String body

    def static make(Closure closure){
        EmailDsl emailDsl = new EmailDsl()
        closure.delegate = emailDsl
        //closure.call()
        closure()
    }

    def to(String toText) {
        this.toText = toText
    }

    def from(String fromText) {
        this.fromText = fromText
    }

    def body(String bodyText) {
        this.body = bodyText
    }
}
