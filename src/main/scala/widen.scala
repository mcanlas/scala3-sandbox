object TestApp extends App {
  enum NumberEnum:
    case Singular
    case Plural

  enum PersonEnum:
    case First
    case Second
    case Third

  sealed trait NumberSealedTrait

  object NumberSealedTrait:
    case object Singular extends NumberSealedTrait
    case object Plural extends NumberSealedTrait

  sealed trait PersonSealedTrait

  object PersonSealedTrait:
    case object First extends PersonSealedTrait
    case object Second extends PersonSealedTrait
    case object Third extends PersonSealedTrait

  // minimization seems to be:
  // a) the key side of the map needs to be two fields (a tuple2)
  // b) one member of the tuple must be a sealed trait; not at enum
  lazy val tupleKeyMap = Map(
    (NumberEnum.Singular, PersonSealedTrait.First) -> "apple",
    (NumberEnum.Plural, PersonSealedTrait.Third) -> "zapple"
  )

  // scala3 compiler complains that union of first | third is required, but person was given
  tupleKeyMap.map { case ((n, p), _) =>
    tupleKeyMap(n -> p)
  }
}
