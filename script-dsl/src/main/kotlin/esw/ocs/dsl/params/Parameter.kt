package esw.ocs.dsl.params

import csw.params.core.generics.Parameter

// throws exception
operator fun <A> Parameter<A>.invoke(index: Int): A {
    return jGet(index).get()
}

val <A> Parameter<A>.values: List<A>
    get() = jValues().toList()