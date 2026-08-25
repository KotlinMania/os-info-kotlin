import Testing
import OsInfo

@Test func testBasicExport() {
    let unknown = Info.Companion.shared.unknown()
    #expect(unknown.bitness == Bitness.Unknown)
    #expect(unknown.architecture == nil)
    #expect(unknown.edition == nil)
    #expect(unknown.codename == nil)
}
