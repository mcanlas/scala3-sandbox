object TestApp extends App {
  enum PersonEnum:
    case First
    case Second
    case Third

  sealed trait PersonSealedTrait

  object PersonSealedTrait:
    case object First extends PersonSealedTrait
    case object Second extends PersonSealedTrait
    case object Third extends PersonSealedTrait

  // minimization seems to be:
  // a) the key side of the map needs to be two fields (a tuple2)
  // b) one member of the tuple must be a sealed trait; not at enum
  lazy val tupleKeyMap = Map(
    ("apple", PersonSealedTrait.First) -> "apple",
    ("notapple", PersonSealedTrait.Third) -> "zapple"
  )

  // possible bug or confusing intuition:
  //   - inferred type of map's key does not match type given to pattern in case match
  //   - scala3 compiler complains that union of first | third is required, but person sealed trait was given from `p`
  tupleKeyMap.map { case ((n, p), _) =>
    tupleKeyMap.apply(n -> p)
  }
}
