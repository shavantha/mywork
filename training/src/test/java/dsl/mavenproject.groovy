package dsl


job("Maven-project-via-dsl") {
    description("maven project on ${new Date()}")
    scm {
        git("https://github.com/shavantha/helloworld.git", 'master')
    }
    triggers {
        scm('* * * * *')
    }
    steps {
        maven('clean package', 'pom.xml')
    }
    publishers {
        archiveArtifacts '**/*.war'
    }
}

